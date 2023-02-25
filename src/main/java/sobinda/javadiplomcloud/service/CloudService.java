package sobinda.javadiplomcloud.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sobinda.javadiplomcloud.dto.CloudFileDto;
import sobinda.javadiplomcloud.entity.CloudFileEntity;
import sobinda.javadiplomcloud.entity.UserEntity;
import sobinda.javadiplomcloud.repository.CloudRepository;
import sobinda.javadiplomcloud.security.JWTToken;
import sobinda.javadiplomcloud.util.CloudManager;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CloudService {

    private final CloudManager cloudManager;
    private final JWTToken jwtToken;
    private final CloudRepository cloudRepository;

    @SneakyThrows
    @Transactional(rollbackOn = {IOException.class})
    public void uploadFile(MultipartFile multipartFile, String fileName) {
        int userId = jwtToken.getAuthenticatedUser().getId();

        CloudFileEntity cloudFileEntity = CloudFileEntity.builder()
                .fileName(fileName)
                .size(multipartFile.getSize())
                .date(Instant.now())
                .key(UUID.randomUUID())
                .userEntity(
                        UserEntity.builder()
                                .id(userId)
                                .build())
                .build();

        log.info(cloudFileEntity.toString());
        var cloudId = cloudRepository.save(cloudFileEntity).getId();
        if (cloudRepository.findById(cloudId).isPresent()) {
            log.info("Файл {} записан в БД под id {}", cloudFileEntity, cloudId);
        }

        cloudManager.upload(multipartFile.getBytes(), cloudFileEntity.getKey().toString(), cloudFileEntity.getFileName());
//        cloudManager.upload(multipartFile.getContentType(), cloudFileEntity.getKey().toString(), cloudFileEntity.getFileName());
        log.info("Файл записан на сервер");
//        return cloudFileEntity;
    }

    public String deleteFile() {
        return null;
    }

    @Transactional
    public CloudFileDto getFile(String fileName) {
        int userId = jwtToken.getAuthenticatedUser().getId();
        log.info("Получаем ID пользователя по токену: {}", userId);
        log.info("Начинаем искать файл в БД: {}", fileName);
        var cloudFile = cloudRepository.findCloudFileEntityByFileName(userId, fileName);
        if (cloudFile.isPresent()) {
            log.info("Файл {} найден на диске. Начинаем чтение байтов", cloudFile);
            var resourceFromBd = cloudFile.map(cloudManager::getFile).get();
            return CloudFileDto.builder()
                    .fileName(fileName)
                    .resource(resourceFromBd)
                    .build();
        }
        throw new FileSystemNotFoundException("Такого файла нет в базе данных");
    }

    public String putFile() {
        return null;
    }

    public List<CloudFileDto> getAllFile() {
        int userId = jwtToken.getAuthenticatedUser().getId();

        var cloudFileEntityList = cloudRepository.findAllByUserId(userId);
        return cloudFileEntityList.stream()
                .map(file -> CloudFileDto.builder()
                        .fileName(file.getFileName())
                        .key(file.getKey())
                        .date(file.getDate())
                        .size(file.getSize())
                        .build())
                .collect(Collectors.toList());
    }
}

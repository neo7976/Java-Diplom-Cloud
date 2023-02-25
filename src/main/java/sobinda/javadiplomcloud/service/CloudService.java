package sobinda.javadiplomcloud.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sobinda.javadiplomcloud.entity.CloudFileEntity;
import sobinda.javadiplomcloud.entity.UserEntity;
import sobinda.javadiplomcloud.repository.CloudRepository;
import sobinda.javadiplomcloud.security.JWTToken;
import sobinda.javadiplomcloud.util.CloudManager;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

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

        cloudRepository.save(cloudFileEntity);
//        cloudManager.upload(multipartFile.getBytes(), cloudFile.getKey().toString(), cloudFile.getFileName());
        cloudManager.upload(multipartFile.getContentType(), cloudFileEntity.getKey().toString(), cloudFileEntity.getFileName());
//        return cloudFileEntity;
    }

    public String deleteFile() {
        return null;
    }

    @Transactional
    public String getFile(Integer id) {
        var cloudFile = cloudRepository.findById(id);
        return cloudFile.map(cloudManager::getFile).orElse(null);
    }

    public String putFile() {
        return null;
    }

    public List<CloudFileEntity> getAllFile() {
        return cloudRepository.findAll();
    }
}

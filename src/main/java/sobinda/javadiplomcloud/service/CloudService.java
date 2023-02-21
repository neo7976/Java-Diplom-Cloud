package sobinda.javadiplomcloud.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sobinda.javadiplomcloud.entity.CloudFileEntity;
import sobinda.javadiplomcloud.repository.CloudRepository;
import sobinda.javadiplomcloud.util.CloudManager;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CloudService {

    private final CloudManager cloudManager;
    private final CloudRepository cloudRepository;

    @SneakyThrows
    @Transactional(rollbackOn = {IOException.class})
    public CloudFileEntity uploadFile(MultipartFile multipartFile) {
        CloudFileEntity cloudFileEntity = CloudFileEntity.builder()
                .fileName(multipartFile.getName())
                .size(multipartFile.getSize())
                .build();

        cloudFileEntity = cloudRepository.save(cloudFileEntity);
//        cloudManager.upload(multipartFile.getBytes(), cloudFile.getKey().toString(), cloudFile.getFileName());
        cloudManager.upload(multipartFile.getContentType(), cloudFileEntity.getKey().toString(), cloudFileEntity.getFileName());
        return cloudFileEntity;
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

    public String getAllFile() {
        return null;
    }
}

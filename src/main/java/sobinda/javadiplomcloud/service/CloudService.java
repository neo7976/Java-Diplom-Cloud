package sobinda.javadiplomcloud.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sobinda.javadiplomcloud.entity.CloudFile;
import sobinda.javadiplomcloud.repository.CloudRepository;
import sobinda.javadiplomcloud.util.CloudManager;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
public class CloudService {
    @Autowired
    CloudManager cloudManager;
    @Autowired
    CloudRepository cloudRepository;

    @SneakyThrows
    @Transactional(rollbackOn = {IOException.class})
    public CloudFile uploadFile(MultipartFile multipartFile) {
        CloudFile cloudFile = CloudFile.builder()
                .fileName(multipartFile.getName())
                .size(multipartFile.getSize())
                .build();

        cloudFile = cloudRepository.save(cloudFile);
//        cloudManager.upload(multipartFile.getBytes(), cloudFile.getKey().toString(), cloudFile.getFileName());
        cloudManager.upload(multipartFile.getContentType(), cloudFile.getKey().toString(), cloudFile.getFileName());
        return cloudFile;
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

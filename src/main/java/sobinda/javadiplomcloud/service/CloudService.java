package sobinda.javadiplomcloud.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sobinda.javadiplomcloud.entity.CloudFile;
import sobinda.javadiplomcloud.repository.CloudRepositoryImp;
import sobinda.javadiplomcloud.util.CloudManager;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
public class CloudService {

    private final CloudRepositoryImp cloudRepository;
    private final CloudManager cloudManager;

    public CloudService(CloudRepositoryImp cloudRepository, CloudManager cloudManager) {
        this.cloudRepository = cloudRepository;
        this.cloudManager = cloudManager;
    }

    @SneakyThrows
    @Transactional(rollbackOn = {IOException.class})
    public CloudFile uploadFile(MultipartFile multipartFile) {
        CloudFile cloudFile = CloudFile.builder()
                .fileName(multipartFile.getName())
                .size(multipartFile.getSize())
                .build();
        cloudFile = cloudRepository.uploadFile(cloudFile);
        cloudManager.upload(multipartFile.getBytes(), cloudFile.getKey().toString());
        return cloudFile;
    }

    public String deleteFile() {
        return null;
    }

    public List<String> getFile() {
        return null;
    }

    public String putFile() {
        return null;
    }

    public String getAllFile() {
        return null;
    }
}

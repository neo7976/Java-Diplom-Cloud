package sobinda.javadiplomcloud.service;

import org.springframework.stereotype.Service;
import sobinda.javadiplomcloud.repository.CloudRepositoryImp;

import java.util.List;

@Service
public class CloudService {

    private final CloudRepositoryImp cloudRepository;

    public CloudService(CloudRepositoryImp cloudRepository) {
        this.cloudRepository = cloudRepository;
    }

    public String uploadFile() {
        return null;
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

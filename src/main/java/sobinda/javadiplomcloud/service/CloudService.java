package sobinda.javadiplomcloud.service;

import org.springframework.stereotype.Service;
import sobinda.javadiplomcloud.repository.CloudRepositoryImp;

@Service
public class CloudService {

    private final CloudRepositoryImp cloudRepository;

    public CloudService(CloudRepositoryImp cloudRepository) {
        this.cloudRepository = cloudRepository;
    }
}

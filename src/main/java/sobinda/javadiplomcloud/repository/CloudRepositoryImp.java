package sobinda.javadiplomcloud.repository;

import org.springframework.stereotype.Repository;
import sobinda.javadiplomcloud.entity.CloudFile;

@Repository
public class CloudRepositoryImp {

    private final CloudRepository cloudRepository;

    public CloudRepositoryImp(CloudRepository cloudRepository) {
        this.cloudRepository = cloudRepository;
    }

    public CloudFile uploadFile(CloudFile cloudFile) {
        //todo написать реализацию
        cloudRepository.save(cloudFile);
        return cloudFile;
    }
}

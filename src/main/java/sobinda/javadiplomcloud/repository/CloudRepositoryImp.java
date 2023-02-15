package sobinda.javadiplomcloud.repository;

import org.springframework.stereotype.Repository;

@Repository
public class CloudRepositoryImp {

    private final CloudRepository cloudRepository;

    public CloudRepositoryImp(CloudRepository cloudRepository) {
        this.cloudRepository = cloudRepository;
    }
}

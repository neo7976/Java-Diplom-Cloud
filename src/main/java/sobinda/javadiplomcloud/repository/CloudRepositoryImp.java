package sobinda.javadiplomcloud.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CloudRepositoryImp {

    private final CloudRepository cloudRepository;

    public CloudRepositoryImp(CloudRepository cloudRepository) {
        this.cloudRepository = cloudRepository;
    }
}

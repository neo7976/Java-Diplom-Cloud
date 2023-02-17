package sobinda.javadiplomcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sobinda.javadiplomcloud.entity.CloudFile;

@Repository
public interface CloudRepository extends JpaRepository<CloudFile, Integer> {
}

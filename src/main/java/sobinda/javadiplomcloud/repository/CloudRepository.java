package sobinda.javadiplomcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sobinda.javadiplomcloud.entity.CloudFile;

public interface CloudRepository extends JpaRepository<CloudFile, Integer> {
}

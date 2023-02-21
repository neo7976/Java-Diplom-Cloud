package sobinda.javadiplomcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sobinda.javadiplomcloud.entity.CloudFileEntity;

@Repository
public interface CloudRepository extends JpaRepository<CloudFileEntity, Integer> {
}

package sobinda.javadiplomcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sobinda.javadiplomcloud.entity.CloudFileEntity;

import java.util.List;

@Repository
public interface CloudRepository extends JpaRepository<CloudFileEntity, Integer> {

    @Query(value = "select * from cloud_file c where c.user_id = :userId", nativeQuery = true)
    List<CloudFileEntity> findAllByUserId(@Param("userId") int userId);
}

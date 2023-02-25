package sobinda.javadiplomcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sobinda.javadiplomcloud.entity.CloudFileEntity;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

@Repository
public interface CloudRepository extends JpaRepository<CloudFileEntity, Integer> {


    @Query(value = "select * from cloud_file c where c.user_id = :userId", nativeQuery = true)
    List<CloudFileEntity> findAllByUserId(@Param("userId") int userId);


    @Query(value = "select * from cloud_file c where c.user_id = :userId and c.file_name= :fileName", nativeQuery = true)
    Optional<CloudFileEntity> findCloudFileEntityByFileName(@Param("userId") int userId,
                                                            @Param("fileName") String fileName);
}

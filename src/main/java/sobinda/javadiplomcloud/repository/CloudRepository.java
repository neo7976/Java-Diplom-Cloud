package sobinda.javadiplomcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sobinda.javadiplomcloud.entity.CloudFileEntity;

import java.util.Optional;

@Repository
public interface CloudRepository extends JpaRepository<CloudFileEntity, Integer> {


//    @Query(value = "select * from cloud_file c where c.user_id = :userId and c.file_name= :fileName", nativeQuery = true)
//    Optional<CloudFileEntity> findCloudFileEntityByFileName(@Param("userId") int userId,
//                                                            @Param("fileName") String fileName);

    @Query(value = "select * from cloud_file c where c.file_name =:fileName", nativeQuery = true)
    Optional<CloudFileEntity> findCloudFileEntityByFileName(@Param("fileName") String fileName);


//    @Modifying
//    @Query(value = "update cloud_file c set c.file_name =:fileName where id=:id", nativeQuery = true)
//    void findByIdAndRenameFileName(@Param("id") int id,
//                                   @Param("fileName") String fileName);

    @Modifying
    @Query(value = "update cloud_file c set c.file_name =:fileName where c.id=:id", nativeQuery = true)
    void findByIdAndRenameFileName(@Param("id") Integer id, @Param("fileName") String fileName);
}

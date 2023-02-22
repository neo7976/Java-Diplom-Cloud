package sobinda.javadiplomcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sobinda.javadiplomcloud.entity.UserEntity;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByLogin(@NotBlank String login);


    @Query(value = "select o.password from UserEntity o")
    List<String> findAllByLogin();
}

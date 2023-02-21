package sobinda.javadiplomcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sobinda.javadiplomcloud.entity.UserEntity;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Integer> {
}

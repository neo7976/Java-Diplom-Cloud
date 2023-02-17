package sobinda.javadiplomcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sobinda.javadiplomcloud.entity.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
}

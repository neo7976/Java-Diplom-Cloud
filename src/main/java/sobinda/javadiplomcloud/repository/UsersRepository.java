package sobinda.javadiplomcloud.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import sobinda.javadiplomcloud.entity.User;

public interface UsersRepository extends JpaRepository<User, Integer> {
}

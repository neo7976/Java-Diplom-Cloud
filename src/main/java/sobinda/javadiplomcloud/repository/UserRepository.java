package sobinda.javadiplomcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sobinda.javadiplomcloud.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByLogin(String login);

    @Query(value = "select User from User where User.id<:id")
    List<User> findAllByIdWhereIdLess(@Param("id") Integer id);
}

package sobinda.javadiplomcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sobinda.javadiplomcloud.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}

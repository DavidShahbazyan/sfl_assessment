package am.davsoft.sfl_assessment.repository;

import am.davsoft.sfl_assessment.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author David Shahbazyan
 * @since Jul 20, 2020
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String username);
}

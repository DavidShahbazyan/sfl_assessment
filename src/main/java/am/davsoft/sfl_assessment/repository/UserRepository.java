package am.davsoft.sfl_assessment.repository;

import am.davsoft.sfl_assessment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author David Shahbazyan
 * @since Jul 20, 2020
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

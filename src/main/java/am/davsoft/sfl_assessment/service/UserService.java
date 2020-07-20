package am.davsoft.sfl_assessment.service;

import am.davsoft.sfl_assessment.model.User;

import java.util.List;

/**
 * @author David Shahbazyan
 * @since Jul 20, 2020
 */
public interface UserService {
    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void delete(Long id);
}

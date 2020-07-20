package am.davsoft.sfl_assessment.service.impl;

import am.davsoft.sfl_assessment.model.Role;
import am.davsoft.sfl_assessment.model.Status;
import am.davsoft.sfl_assessment.model.User;
import am.davsoft.sfl_assessment.repository.RoleRepository;
import am.davsoft.sfl_assessment.repository.UserRepository;
import am.davsoft.sfl_assessment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author David Shahbazyan
 * @since Jul 20, 2020
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            List<Role> userRoles = new ArrayList<>();
            userRoles.add(roleRepository.findByName("ROLE_USER"));
            user.setRoles(userRoles);
        } else {
            // I know this is the worst way I could chose, but I have a lot of staff to implement yet :(
            user.setRoles(user.getRoles().stream()
                    .map(role -> roleRepository.findByName(role.getName()))
                    .collect(Collectors.toList()));
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);

        Date now = new Date();

        user.setCreated(now);
        user.setUpdated(now);

        User newUser = userRepository.save(user);
        log.info("IN register - user successfully created by username: {}", newUser.getUsername());

        return newUser;
    }

    @Override
    public List<User> getAll() {
        List<User> userList = userRepository.findAll();
        log.info("IN getAll - {} users found", userList.size());
        return userList;
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        log.info("IN findByUsername - user found by username: {}", username);
        return user;
    }

    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }
        log.info("IN findById - user found by id: {}", id);
        return user;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted", id);
    }
}

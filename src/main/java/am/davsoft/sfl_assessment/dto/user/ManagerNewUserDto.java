package am.davsoft.sfl_assessment.dto.user;

import am.davsoft.sfl_assessment.model.Role;
import am.davsoft.sfl_assessment.model.Status;
import am.davsoft.sfl_assessment.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author David Shahbazyan
 * @since Jul 20, 2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ManagerNewUserDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String status;
    private List<String> roles;

    public User toModel() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        if (status != null) {
            user.setStatus(Status.valueOf(status));
        }
        if (roles != null) {
            user.setRoles(roles.stream().map(s -> {
                Role role = new Role();
                role.setName(s);
                return role;
            }).collect(Collectors.toList()));
        }
        return user;
    }
}

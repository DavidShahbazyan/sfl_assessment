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
public class ManagerUserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String status;
    private List<String> roles;

    public User toModel() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
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

    public static ManagerUserDto fromModel(User model) {
        if (model == null) {
            return null;
        }
        ManagerUserDto managerUserDto = new ManagerUserDto();
        managerUserDto.setId(model.getId());
        managerUserDto.setUsername(model.getUsername());
        managerUserDto.setFirstName(model.getFirstName());
        managerUserDto.setLastName(model.getLastName());
        managerUserDto.setEmail(model.getEmail());
        managerUserDto.setStatus(model.getStatus().name());
        if (model.getRoles() != null) {
            managerUserDto.setRoles(model.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
        }
        return managerUserDto;
    }
}

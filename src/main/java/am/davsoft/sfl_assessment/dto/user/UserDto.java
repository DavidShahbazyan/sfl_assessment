package am.davsoft.sfl_assessment.dto.user;

import am.davsoft.sfl_assessment.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author David Shahbazyan
 * @since Jul 20, 2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;

    public User toModel() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        return user;
    }

    public static UserDto fromModel(User model) {
        if (model == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(model.getId());
        userDto.setUsername(model.getUsername());
        userDto.setFirstName(model.getFirstName());
        userDto.setLastName(model.getLastName());
        userDto.setEmail(model.getEmail());
        return userDto;
    }
}

package am.davsoft.sfl_assessment.dto;

import lombok.Data;

/**
 * @author David Shahbazyan
 * @since Jul 20, 2020
 */
@Data
public class AuthenticationRequestDto {
    private String username;
    private String password;
}

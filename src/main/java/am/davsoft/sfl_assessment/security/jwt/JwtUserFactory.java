package am.davsoft.sfl_assessment.security.jwt;

import am.davsoft.sfl_assessment.model.Status;
import am.davsoft.sfl_assessment.model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.stream.Collectors;

/**
 * @author David Shahbazyan
 * @since Jul 20, 2020
 */
public final class JwtUserFactory {
    private JwtUserFactory() {
    }

    public static JwtUser newUser(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getStatus().equals(Status.ACTIVE),
                user.getUpdated(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }
}

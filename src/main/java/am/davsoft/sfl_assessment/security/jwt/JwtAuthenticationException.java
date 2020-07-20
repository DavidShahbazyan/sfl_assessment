package am.davsoft.sfl_assessment.security.jwt;

import org.springframework.security.core.AuthenticationException;

/**
 * @author David Shahbazyan
 * @since Jul 20, 2020
 */
public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}

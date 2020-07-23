package am.davsoft.sfl_assessment.rest.api;

import am.davsoft.sfl_assessment.dto.AuthenticationRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author David Shahbazyan
 * @since Jul 23, 2020
 */
@RequestMapping(value = "/api/default/auth")
public interface AuthenticationRestController {
    @PostMapping("/login")
    ResponseEntity<Map<Object, Object>> login(@RequestBody AuthenticationRequestDto requestDto);
}

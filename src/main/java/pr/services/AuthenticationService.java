package pr.services;

import org.springframework.security.core.Authentication;
import pr.models.User;


import java.util.List;
import java.util.Map;

public interface AuthenticationService {

    Long getUserIdByAuthentication(Authentication authentication);

    User getUserByAuthentication(Authentication authentication);

}

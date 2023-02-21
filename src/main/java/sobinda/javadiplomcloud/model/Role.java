package sobinda.javadiplomcloud.model;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {
    ADMIN("ROLE_ADMIN"),
    READ("ROLE_READ"),
    WRITE("ROLE_WRITE"),
    USER("ROLE_WRITE"),
    GUEST("ROLE_GUEST");

    private final String nameRole;

    @Override
    public String getAuthority() {
        return nameRole;
    }
}

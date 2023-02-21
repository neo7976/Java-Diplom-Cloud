package sobinda.javadiplomcloud.security;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import sobinda.javadiplomcloud.model.Role;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
//аннотация для лога
@Slf4j
@RequiredArgsConstructor
public class JWTFilter extends GenericFilterBean {

    private final JWTToken jwtToken;

    private String getTokenFromRequest(HttpServletRequest request) {
        return "НАПИСАТЬ ВОЗВРАЩАЕМЫЙ ТОКЕН";
    }

    private static Set<Role> getRoles(Claims claims) {
        List<String> roles = claims.get("roles", List.class);
        return roles.stream().map(Role::valueOf)
                .collect(Collectors.toSet());
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    }
}

package sobinda.javadiplomcloud.security;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import sobinda.javadiplomcloud.config.AuthenticationConfigConstants;
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
@Slf4j
@RequiredArgsConstructor
public class JWTFilter extends GenericFilterBean {

    private final JWTToken jwtToken;

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(AuthenticationConfigConstants.AUTH_TOKEN);
        if (StringUtils.hasText(bearer) && bearer.startsWith(AuthenticationConfigConstants.TOKEN_PREFIX)) {
            return bearer.substring(7);
        }
        return null;
    }

    private static Set<Role> getRoles(Claims claims) {
        List<String> roles = claims.get("roles", List.class);
        return roles.stream().map(Role::valueOf)
                .collect(Collectors.toSet());
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = getTokenFromRequest((HttpServletRequest) servletRequest);
        if (token != null && jwtToken.validateAccessToken(token)) {
            log.info("Токен активен: {}", token);
            Claims claims = jwtToken.getAccessClaims(token);

            JWTAuthentication jwtAuthentication = new JWTAuthentication();
            jwtAuthentication.setRoles(getRoles(claims));
            jwtAuthentication.setUsername(claims.getSubject());
            jwtAuthentication.setAuthenticated(true);

            SecurityContextHolder.getContext().setAuthentication(jwtAuthentication);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

package pl.devapo.product.listening.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@Order(3)
@RequiredArgsConstructor
class JwtAuthorizationFilter extends OncePerRequestFilter {
    public static final Logger log = LoggerFactory.getLogger(JwtAuthorizationFilter.class);
    private final SecurityConfigProperties securityConfigProperties;
    private final TokenParser tokenParser;

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws IOException, ServletException {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(securityConfigProperties.getJwtSecret().getBytes())
                    .parseClaimsJws(tokenParser.getToken(req))
                    .getBody();
            String username = claims.getSubject();
            @SuppressWarnings("unchecked")
            List<String> authorities = claims.get("authorities", List.class);
            if (username != null) {
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        authorities.stream()
                                .map(SimpleGrantedAuthority::new)
                                .collect(toList()));
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(auth);
            }
        } catch (Exception ignore) {
            SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(req, response);
    }
}


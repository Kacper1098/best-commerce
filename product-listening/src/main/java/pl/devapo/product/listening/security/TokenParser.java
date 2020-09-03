package pl.devapo.product.listening.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class TokenParser {
    private final SecurityConfigProperties securityConfigProperties;

    public String getToken(HttpServletRequest request) {
        String token = request.getHeader(securityConfigProperties.getTokenHeader());
        if (token != null && token.startsWith(securityConfigProperties.getTokenPrefix())) {
            return token.replace(securityConfigProperties.getTokenPrefix() + " ", "");
        }
        throw new BadTokenException("Incorrect request. No valid token header");
    }
}


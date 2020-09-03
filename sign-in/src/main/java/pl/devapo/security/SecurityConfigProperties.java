package pl.devapo.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "best-commerce.security")
@EnableConfigurationProperties({SecurityConfigProperties.class})
@Getter
@Setter
public class SecurityConfigProperties {
    private String loginUrl;
    private String jwtSecret;
    private String tokenHeader;
    private String tokenPrefix;
    private Long tokenTtlSeconds;
    private Long tokenRememberMeTtlSeconds;
}

package pl.devapo.product.listening.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "best-product.security")
@EnableConfigurationProperties({SecurityConfigProperties.class})
@Getter
@Setter
public class SecurityConfigProperties {
    private String jwtSecret;
    private String tokenHeader;
    private String tokenPrefix;
}

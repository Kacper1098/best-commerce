package pl.devapo.swaggerConfiguration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration("swaggerConfigProperties")
public class SwaggerConfigProperties {

    @Value("${api.version}")
    private String apiVersion;

    @Value("${swagger.enabled}")
    private Boolean enabled = false;

    @Value("${swagger.title}")
    private String title;

    @Value("${swagger.description}")
    private String description;

    @Value("${swagger.useDefaultResponseMessages}")
    private Boolean useDefaultResponseMessages;

    @Value("${swagger.enableUrlTemplating}")
    private Boolean enableUrlTemplating;

    @Value("${swagger.deepLinking}")
    private Boolean deepLinking;

    @Value("${swagger.defaultModelsExpandDepth}")
    private Integer defaultModelsExpandDepth;

    @Value("${swagger.defaultModelExpandDepth}")
    private Integer defaultModelExpandDepth;

    @Value("${swagger.displayOperationId}")
    private Boolean displayOperationId;

    @Value("${swagger.displayRequestDuration}")
    private Boolean displayRequestDuration;

    @Value("${swagger.filter}")
    private Boolean filter;

    @Value("${swagger.maxDisplayedTags}")
    private Integer maxDisplayedTags;

    @Value("${swagger.showExtensions}")
    private Boolean showExtensions;

}
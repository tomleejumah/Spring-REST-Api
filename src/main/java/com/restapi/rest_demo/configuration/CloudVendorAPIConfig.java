package com.restapi.rest_demo.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudVendorAPIConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cloud Vendor Api Docs")
                        .version("1.0")
                        .description("A detailed description of CloudVendor API")
                        .termsOfService("http://your-terms-of-service-url.com")// todo generate
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0")) //todo
                        .contact(new Contact()
                                .name("API Support Tomlee")
                                .url("http://your-support-url.com") // todo
                                .email("support@your-company.com")) // todo
                );
    }
}

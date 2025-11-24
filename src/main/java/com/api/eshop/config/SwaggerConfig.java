package com.api.eshop.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Eshop API")
                        .version("1.0.0")
                        .description("API documentation for Eshop"))
                // ✅ آدرس صحیح سرور (پروکسی شده از طریق IIS)
                .servers(List.of(
                        new Server().url("https://rjland.ir/api").description("Production Server")
                ));
    }
}

package com.api.eshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class WebConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration configuration = new CorsConfiguration();

        // ✅ فقط دامنه‌های معتبر را مجاز کن
        configuration.setAllowedOriginPatterns(List.of(
                "http://localhost:3000",
                "https://rjland.ir"
        ));

        // ✅ فعال‌سازی Credentials (برای ارسال کوکی یا Authorization header)
        configuration.setAllowCredentials(true);

        // ✅ متدها و Headerهای مجاز
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return new CorsFilter(source);
    }
}

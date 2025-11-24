package com.api.eshop.security;

import com.api.eshop.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint unauthorizedHandler;
    private final CustomUserDetailsService customUserDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final HandlerMappingIntrospector introspector;

    public SecurityConfig(
            JwtAuthenticationEntryPoint unauthorizedHandler,
            CustomUserDetailsService customUserDetailsService,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            JwtAuthenticationFilter jwtAuthenticationFilter,
            HandlerMappingIntrospector introspector
    ) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.customUserDetailsService = customUserDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.introspector = introspector;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // ✅ فعال‌سازی CORS و غیرفعال کردن CSRF
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())

                // ✅ تنظیم مدیریت سشن
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // ✅ هندلر خطای JWT
                .exceptionHandling(ex -> ex.authenticationEntryPoint(unauthorizedHandler))

                // ✅ تعیین مسیرهای آزاد و محافظت‌شده
                .authorizeHttpRequests(auth -> auth
                        // مسیر لاگین بدون نیاز به احراز هویت
                        .requestMatchers(new AntPathRequestMatcher("/users/login")).permitAll()

                        // مسیر محصولات برای همه آزاد
                        .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()

                        // مسیر Swagger برای همه آزاد
                        .requestMatchers(
                                new AntPathRequestMatcher("/swagger-ui/**"),
                                new AntPathRequestMatcher("/v3/api-docs/**"),
                                new AntPathRequestMatcher("/swagger-ui.html")
                        ).permitAll()

                        // مسیر فایل‌های استاتیک (تصاویر، CSS، JS)
                        .requestMatchers(
                                new AntPathRequestMatcher("/uploads/**"),
                                new AntPathRequestMatcher("/favicon.ico"),
                                new AntPathRequestMatcher("/**/*.png"),
                                new AntPathRequestMatcher("/**/*.gif"),
                                new AntPathRequestMatcher("/**/*.jpg"),
                                new AntPathRequestMatcher("/**/*.svg"),
                                new AntPathRequestMatcher("/**/*.html"),
                                new AntPathRequestMatcher("/**/*.css"),
                                new AntPathRequestMatcher("/**/*.js")
                        ).permitAll()

                        // سایر مسیرها فقط با JWT
                        .anyRequest().authenticated()
                );

        // ✅ اضافه کردن فیلتر JWT قبل از UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // ✅ تنظیمات CORS برای Swagger و Frontend
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of(
                "https://rjland.ir",
                "https://www.rjland.ir",
                "http://localhost:3000" // در صورت تست از لوکال
        ));

        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(List.of("Authorization"));
        configuration.setAllowCredentials(true); // برای ارسال هدرها

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

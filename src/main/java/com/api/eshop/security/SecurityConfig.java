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
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

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
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth

                        // مسیر login بدون JWT
                        .requestMatchers(new AntPathRequestMatcher("/users/login")).permitAll()

                        // مسیر عمومی محصولات بدون JWT
                        .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()

                        // فایل‌های استاتیک
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

                        // Swagger UI
                        .requestMatchers(
                                new AntPathRequestMatcher("/swagger-ui/**"),
                                new AntPathRequestMatcher("/v3/api-docs/**"),
                                new AntPathRequestMatcher("/swagger-ui.html")
                        ).permitAll()

                        // سایر مسیرها نیاز به JWT دارند
                        .anyRequest().authenticated()
                );

        // اضافه کردن فیلتر JWT قبل از UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

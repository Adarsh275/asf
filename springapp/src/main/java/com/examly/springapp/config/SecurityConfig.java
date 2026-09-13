package com.examly.springapp.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
 
/**
 * Security configuration for the application.
 *
 * Annotated with `@Configuration` to indicate a Spring configuration
 * class.
 * `@EnableWebSecurity` to enable web security features.
 * `@EnableMethodSecurity` to enable method-level security annotations.
 */
@SuppressWarnings("deprecation")
@EnableMethodSecurity
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
 
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
     
    @Autowired
    public SecurityConfig(AuthenticationProvider authenticationProvider,
                          JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        }
    /**
     * Configures the Spring Security filter chain.
     *
     * @param http the `HttpSecurity` object used to customize security settings.
     *             - Disables CSRF and CORS protections.
     *             - Configures public access to specific API endpoints
     *             (`permitAll`).
     *             - Ensures authentication is required for all other endpoints.
     * @return the configured `SecurityFilterChain` object.
     * @throws Exception if there is an issue configuring the security chain.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors()
                .and()
                .csrf(c -> c.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                        "/api/register",
                        "/api/login",
                        // "/api/feedback",
                        // "/api/feedback/**",
                        // "/api/books",
                        // "/api/bookrentalrequest",
                        // "/api/bookrentalrequest/**",
                        "/api/users",
                        "/api/user",
                        "/api/bookrentalrequest",
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/webjars/**"
                        ).permitAll().anyRequest().authenticated())
                .authenticationProvider(authenticationProvider)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
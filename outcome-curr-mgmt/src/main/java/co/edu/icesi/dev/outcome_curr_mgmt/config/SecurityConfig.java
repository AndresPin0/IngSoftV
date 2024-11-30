package co.edu.icesi.dev.outcome_curr_mgmt.config;

import co.edu.icesi.dev.outcome_curr_mgmt.saamfi.filters.SaamfiAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.http.HttpHeaders;

import java.util.Collections;
import java.util.regex.Pattern;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final SaamfiAuthenticationFilter saamfiAuthenticationFilter;

    public SecurityConfig(SaamfiAuthenticationFilter saamfiAuthenticationFilter) {
        this.saamfiAuthenticationFilter = saamfiAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

        // Permitir el acceso a la consola H2 y otras rutas públicas
        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()  // H2 Console
                .requestMatchers(new AntPathRequestMatcher("/v1/auth/users/login")).permitAll()  // Login
                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll()  // Swagger
                .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**")).permitAll()  // API Docs
                .requestMatchers(new AntPathRequestMatcher("/outcurrapi/**")).permitAll()  // Context Path
                .requestMatchers(new AntPathRequestMatcher("/actuator/**")).permitAll()  // Actuator
                .requestMatchers(new AntPathRequestMatcher("/**")).authenticated()  // Requiere autenticación
        );

        // Configurar sesión sin estado
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Agregar filtro personalizado (opcional durante pruebas)
        http.addFilterBefore(saamfiAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration configuration = new CorsConfiguration();

            // Permitir todos los orígenes durante pruebas locales
            configuration.addAllowedOriginPattern("*");
            configuration.addAllowedMethod("*");
            configuration.addAllowedHeader("*");

            return configuration;
        };
    }
}

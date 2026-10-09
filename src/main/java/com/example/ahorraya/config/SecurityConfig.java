package com.example.ahorraya.config;


import com.example.ahorraya.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.GET,
                                "/api/categorias/**", "/api/marcas/**", "/api/empresas/**",
                                "/api/productos/**", "/api/sedes/**", "/api/productos-sede/**").permitAll()
                        .requestMatchers(HttpMethod.POST,
                                "/api/categorias/**", "/api/marcas/**", "/api/empresas/**",
                                "/api/productos/**", "/api/sedes/**", "/api/productos-sede/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT,
                                "/api/categorias/**", "/api/marcas/**", "/api/empresas/**",
                                "/api/productos/**", "/api/sedes/**", "/api/productos-sede/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/**").hasAuthority("ADMIN")
                        .requestMatchers("/api/clientes/**", "/api/compras/**", "/api/resenas/**").hasAnyAuthority("CLIENTE", "ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}

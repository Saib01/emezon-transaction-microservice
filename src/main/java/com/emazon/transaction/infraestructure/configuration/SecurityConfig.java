package com.emazon.transaction.infraestructure.configuration;

import com.emazon.transaction.infraestructure.configuration.jwt.JwtAuthenticationFilter;
import com.emazon.transaction.infraestructure.configuration.jwt.JwtRequestInterceptor;
import com.emazon.transaction.infraestructure.configuration.jwt.JwtUtils;
import com.emazon.transaction.infraestructure.exceptionhandler.CustomAccessDeniedHandler;
import com.emazon.transaction.infraestructure.exceptionhandler.JwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.emazon.transaction.infraestructure.util.InfraestructureRestControllerConstants.API_ADD_SUPPLY;
import static com.emazon.transaction.infraestructure.util.InfraestructureRestControllerConstants.API_PURCHASE;
import static com.emazon.transaction.infraestructure.util.InfrastructureConstants.AUX_BODEGA;
import static com.emazon.transaction.infraestructure.util.InfrastructureConstants.CLIENT;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtUtils jwtUtils;
    private final JwtRequestInterceptor jwtRequestInterceptor;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(http -> {
                    http.requestMatchers(HttpMethod.PUT, API_ADD_SUPPLY).hasRole(AUX_BODEGA);
                    http.requestMatchers(HttpMethod.POST, API_PURCHASE).hasRole(CLIENT);
                    http.anyRequest().permitAll();
                })
                .addFilterBefore(new JwtAuthenticationFilter(jwtUtils, jwtRequestInterceptor), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler(customAccessDeniedHandler)
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .build();
    }

}
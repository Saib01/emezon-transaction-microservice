package com.emazon.transaction.infraestructure.output.jpa.adapters;


import com.emazon.transaction.domain.spi.IAuthenticationPersistencePort;
import com.emazon.transaction.infraestructure.configuration.jwt.JwtRequestInterceptor;
import com.emazon.transaction.infraestructure.configuration.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
public class AuthenticationAdapter implements IAuthenticationPersistencePort {
    private final JwtRequestInterceptor jwtRequestInterceptor;
    private final JwtUtils jwtUtils;
    @Override
    public Long getUserId() {
        return Long.valueOf(
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getPrincipal()
                        .toString()
        );
    }
    @Override
    public String getEmail(){
        return jwtRequestInterceptor.getEmail();
    }
}

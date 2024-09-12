package com.emazon.transaction.infraestructure.configuration.jwt;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.Setter;
import org.springframework.stereotype.Component;

import static com.emazon.transaction.infraestructure.util.InfrastructureConstants.BEARER_PREFIX;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@Setter
public class JwtRequestInterceptor implements RequestInterceptor {

    private String jwtToken;

    @Override
    public void apply(RequestTemplate template) {
        if (jwtToken != null) {
            template.header(AUTHORIZATION, BEARER_PREFIX.concat(jwtToken));
            jwtToken = null;
        }
    }
}

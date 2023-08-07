package com.accenture.tigital.libraries.decorators;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.accenture.tigital.libraries.enums.ErrorScope;
import com.accenture.tigital.libraries.exceptions.RestException;
import com.accenture.tigital.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class BearerAuthAspect {
    @Autowired
    private AuthService authService;
    @Before("@annotation(com.accenture.tigital.libraries.decorators.BearerAuth)")
    public void auth() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();


        if (requestAttributes == null) {
            throw new RestException("Could not retrieve HTTP request", 400, ErrorScope.CLIENT);
        }

        HttpServletRequest request = requestAttributes.getRequest();
        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new RestException("Bearer token is missing or invalid", 401, ErrorScope.CLIENT);
        }

        String token = authorization.substring(7);

        Boolean isVerified = this.authService.verify(token);

        if(isVerified == false) {
            throw new RestException("Invalid token", 401, ErrorScope.CLIENT);
        }

        request.setAttribute("token", token);
    }
}

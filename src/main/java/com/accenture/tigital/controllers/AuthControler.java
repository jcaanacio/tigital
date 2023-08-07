package com.accenture.tigital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.tigital.libraries.decorators.BasicAuth;
import com.accenture.tigital.libraries.decorators.BearerAuth;
import com.accenture.tigital.libraries.typings.TokenResponse;
import com.accenture.tigital.libraries.typings.UserInput;
import com.accenture.tigital.services.AuthService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthControler {
    @Autowired
    private AuthService authService;

    @BasicAuth
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public TokenResponse sign(HttpServletRequest request){
        UserInput userInput = (UserInput) request.getAttribute("requestBody");
        TokenResponse reponse = new TokenResponse(authService.sign(userInput));
        return reponse;
    }

    @BearerAuth
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public TokenResponse refresh(HttpServletRequest request){
        String token = (String) request.getAttribute("token");
        TokenResponse reponse = new TokenResponse(authService.refresh(token));
        return reponse;
    }
}

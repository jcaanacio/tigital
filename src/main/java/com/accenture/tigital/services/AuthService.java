package com.accenture.tigital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.accenture.tigital.libraries.enums.ErrorScope;
import com.accenture.tigital.libraries.exceptions.RestException;
import com.accenture.tigital.libraries.implementations.JwtTokenization;
import com.accenture.tigital.libraries.typings.TokenizationPayload;
import com.accenture.tigital.libraries.typings.UserInput;
import com.accenture.tigital.models.User;
import com.accenture.tigital.repositories.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenization jwtTokenization;

    public String sign(UserInput userInput) {
        User user = userRepository.findByUsername(userInput.getUsername());

        if (user == null) {
            throw new RestException("Invalid credentials", 401, ErrorScope.CLIENT);
        }

        Boolean isPasswordMatch = user.isPasswordMatch(userInput.getPassword());


        if(isPasswordMatch == false) {
            throw new RestException("Invalid credentials", 401, ErrorScope.CLIENT);
        }

        TokenizationPayload payload = new TokenizationPayload(user.getUserId(), user.getUsername(), user.getUserRole());

        return this.jwtTokenization.sign(payload);
    }

    public String refresh(String token) {
        TokenizationPayload payload = jwtTokenization.getAllClaimsFromToken(token);
        User user = userRepository.findByUsername(payload.getUsername());

        if (user == null) {
            throw new RestException("Invalid credentials", 401, ErrorScope.CLIENT);
        }

        return this.jwtTokenization.refresh(payload);
    }

    public Boolean verify(String token) {
        return this.jwtTokenization.verify(token);
    }
}

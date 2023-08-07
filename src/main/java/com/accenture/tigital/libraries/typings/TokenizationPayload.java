package com.accenture.tigital.libraries.typings;

import com.accenture.tigital.libraries.enums.UserRole;
import com.accenture.tigital.libraries.interfaces.ITokenizationPayload;

public class TokenizationPayload implements ITokenizationPayload {

    private Long id;
    private String username;
    private UserRole role;


    public TokenizationPayload (Long id, String username, UserRole role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public UserRole getUserRole() {
        return this.role;
    }

}


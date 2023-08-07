package com.accenture.tigital.libraries.interfaces;

import com.accenture.tigital.libraries.enums.UserRole;

public interface ITokenizationPayload {
    Long getId();
    String getUsername();
    UserRole getUserRole();
}

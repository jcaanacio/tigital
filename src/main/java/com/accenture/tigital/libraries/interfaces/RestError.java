package com.accenture.tigital.libraries.interfaces;

import com.accenture.tigital.libraries.enums.ErrorScope;

public interface RestError {
    String getMessage();
    int getCode();
    ErrorScope getScope();
}

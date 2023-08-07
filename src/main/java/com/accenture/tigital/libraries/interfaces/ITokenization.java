package com.accenture.tigital.libraries.interfaces;

import com.accenture.tigital.libraries.typings.TokenizationPayload;

public interface ITokenization {
    boolean verify(String token);
    String sign(TokenizationPayload payload);
    String refresh(TokenizationPayload payload);
    TokenizationPayload getAllClaimsFromToken(String token);
}

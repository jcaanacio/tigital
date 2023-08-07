package com.accenture.tigital.libraries.interfaces;

public interface IPasswordEncrypter {
    boolean isPasswordMatch(String inputPassword, String encodedPassword);
    String encode(String password);
}

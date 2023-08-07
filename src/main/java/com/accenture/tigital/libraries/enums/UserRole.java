package com.accenture.tigital.libraries.enums;

public enum UserRole {
    ADMIN("ADMIN"),
    MANAGER("MANAGER"),
    EMPLOYEE("EMPLOYEE");

    private final String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    // Optional: You can add additional methods or custom logic to the enum if needed
}


package com.example.kok.enumeration;

public enum UserRole {
    MEMBER("member"), COMPANY("company"), ADMIN("admin");

    private final String value;

    UserRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

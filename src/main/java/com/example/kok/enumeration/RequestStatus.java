package com.example.kok.enumeration;

public enum RequestStatus {
    AWAIT("await"), ACCEPT("accept"), REJECT("reject");

    private final String value;

    RequestStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

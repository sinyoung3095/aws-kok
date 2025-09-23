package com.example.kok.enumeration;

public enum Provider {
    GOOGLE("google"), KAKAO("kakao"), NAVER("naver"), KOK("kok");

    private final String value;

    Provider(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

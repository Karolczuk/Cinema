package com.app.cinema.service.security.tokens;

public interface TokenSettings {

    String SECRET_KEY = "MagdalenaKarolczukSecretKey";
    String TOKEN_PREFIX = "Bearer ";
    String TOKEN_HEADER = "Authorization";

    long ACCESS_TOKEN_EXPIRATION_TIME = 3000_000;
    long REFRESH_TOKEN_EXPIRATION_TIME = 6000_000;
}

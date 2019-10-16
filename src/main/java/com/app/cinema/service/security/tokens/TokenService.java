package com.app.cinema.service.security.tokens;

import com.app.cinema.dto.Tokens;
import com.app.cinema.exceptions.AppException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public interface TokenService {

    public static Tokens createTokens(Authentication authentication) {
        if (authentication == null) {
            throw new AppException("create tokens - authentication object is null");
        }
        return Tokens.builder()
                .accessToken(createToken(authentication, false))
                .refreshToken(createToken(authentication, true))
                .build();
    }

    public static Tokens createTokensFromRefreshToken(String username, String rolesStr) {

        return Tokens.builder()
                .accessToken(createTokenFromRefreshToken(username, rolesStr, false))
                .refreshToken(createTokenFromRefreshToken(username, rolesStr, true))
                .build();
    }

    public static String createToken(Authentication authentication, boolean isRefreshToken) {

        String username = authentication.getName();

        long expirationTime = TokenSettings.ACCESS_TOKEN_EXPIRATION_TIME;
        if (isRefreshToken) {
            expirationTime = TokenSettings.REFRESH_TOKEN_EXPIRATION_TIME;
        }

        String rolesStr = convertRolesToString(authentication);
        if (isRefreshToken) {
            rolesStr = "ROLE_REFRESH," + rolesStr;
        }

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .claim("roles", rolesStr)
                .signWith(SignatureAlgorithm.HS512, TokenSettings.SECRET_KEY)
                .compact();
    }

    public static String createTokenFromRefreshToken(String username, String rolesStr, boolean isRefreshToken) {

        long expirationTime = TokenSettings.ACCESS_TOKEN_EXPIRATION_TIME;
        if (isRefreshToken) {
            expirationTime = TokenSettings.REFRESH_TOKEN_EXPIRATION_TIME;
        }

        if (isRefreshToken) {
            rolesStr = "ROLE_REFRESH," + rolesStr;
        }

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .claim("roles", rolesStr)
                .signWith(SignatureAlgorithm.HS512, TokenSettings.SECRET_KEY)
                .compact();
    }

    public static String convertRolesToString(Authentication authentication) {
        return authentication.getAuthorities()
                .stream()
                .map(auth -> ((GrantedAuthority) auth).getAuthority())
                .collect(Collectors.joining(","));
    }

    public static UsernamePasswordAuthenticationToken parseAccessToken(String accessToken) {

        if (accessToken == null) {
            throw new AppException("parse token - access token is null");
        }

        if (!accessToken.startsWith(TokenSettings.TOKEN_PREFIX)) {
            throw new AppException("parse token - access token doesn't start with right prefix");
        }

        if (!isTokenValid(accessToken)) {
            throw new AppException("parse token - token is not valid");
        }

        String username = getUsername(accessToken);
        List<GrantedAuthority> roles = getRoles(accessToken);
        return new UsernamePasswordAuthenticationToken(username, null, roles);
    }

    public static Tokens parseRefreshToken(String refreshToken) {

        if (refreshToken == null) {
            throw new AppException("parse token - access token is null");
        }

//        if (!refreshToken.startsWith(TokenSettings.TOKEN_PREFIX)) {
//            throw new AppException("parse token - access token doesn't start with right prefix");
//        }

        if (!isTokenValid(refreshToken)) {
            throw new AppException("parse token - token is not valid");
        }

        if (!hasRole(refreshToken, "ROLE_REFRESH")) {
            throw new AppException("parse token - token is not refresh token");
        }

        String username = getUsername(refreshToken);
        String rolesStr = getRolesAsString(refreshToken);
        return createTokensFromRefreshToken(username, rolesStr);
    }

    static Claims getClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(TokenSettings.SECRET_KEY)
                .parseClaimsJws(token.replace(TokenSettings.TOKEN_PREFIX, ""))
                .getBody();
    }

    static String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    static List<GrantedAuthority> getRoles(String token) {
        return Arrays
                .stream(getClaims(token).get("roles").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    static String getRolesAsString(String token) {
        return getClaims(token).get("roles").toString();
    }

    static boolean isTokenValid(String token) {
        return getClaims(token).getExpiration().after(new Date());
    }

    public static boolean hasRole(String token, String role) {
        return getRolesAsString(token).contains(role);
    }
}

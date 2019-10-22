package com.app.cinema.service.security.filters;

import com.app.cinema.service.security.tokens.TokenService;
import com.app.cinema.service.security.tokens.TokenSettings;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        String accessToken = request.getHeader(TokenSettings.TOKEN_HEADER);
        System.out.println("ACCESS TOKEN = " + accessToken);
        if (accessToken != null) {

            System.out.println("xxxxxx");
            UsernamePasswordAuthenticationToken auth = TokenService.parseAccessToken(accessToken);
            SecurityContextHolder.getContext().setAuthentication(auth);
           // chain.doFilter(request, response);

        }
        chain.doFilter(request, response);

    }
}

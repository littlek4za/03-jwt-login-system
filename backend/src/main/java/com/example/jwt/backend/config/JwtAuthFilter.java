package com.example.jwt.backend.config;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.TokenExpiredException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserAuthProvider userAuthProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader((HttpHeaders.AUTHORIZATION));
        String path = request.getServletPath();
        if (path.equals("/login") || path.equals("/register")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (authHeader != null) {
            String[] authElements = authHeader.split(" ");

            if (authElements.length == 2 && "Bearer".equals(authElements[0])) {
                try {
                    if ("GET".equals(request.getMethod())) {
                        SecurityContextHolder.getContext()
                                .setAuthentication(userAuthProvider.validateToken(authElements[1]));
                    } else {
                        SecurityContextHolder.getContext()
                                .setAuthentication(userAuthProvider.validateTokenStrongly(authElements[1]));
                    }

                } catch (TokenExpiredException e) {
                    SecurityContextHolder.clearContext();
                    // response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    // response.getWriter().write("Token expired");
                    throw e;
                }

                // catch (RuntimeException e) {
                //     SecurityContextHolder.clearContext();
                //     response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                //     response.getWriter().write("Invalid expired");
                //     return;
                // }
            }
        }

        filterChain.doFilter(request, response);
    }

}

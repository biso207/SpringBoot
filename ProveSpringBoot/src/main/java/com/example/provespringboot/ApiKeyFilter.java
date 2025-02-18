package com.example.provespringboot;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {
    @Value("${application.security.api-key}")
    private String ak;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String apiKey = request.getHeader("X-API-Key");
        String path = request.getRequestURI();
        System.out.println(path);
        if (isValidApiKey(apiKey)||path.matches("/swagger-ui.*")||path.matches("/api-docs.*")) {
            filterChain.doFilter(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid API Key");
        }
    }

    private boolean isValidApiKey(String apiKey) {
        if(!ak.equals(apiKey)||apiKey==null){
            return false;
        }
        return true; // Placeholder
    }
}
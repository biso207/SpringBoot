package com.example.provespringboot;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class ApiKeyAuthFilter extends OncePerRequestFilter {

    private static final List<String> PUBLIC_URLS = List.of(
            "/swagger-ui", "/swagger-ui/",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/webjars/**"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestUri = request.getRequestURI();
        String apiKey = request.getHeader("X-API-KEY");

        System.out.println("Request URI: " + requestUri);
        System.out.println("API Key ricevuta: " + apiKey);

        // se la richiesta è per swagger la lascia passare
        if (PUBLIC_URLS.stream().anyMatch(requestUri::startsWith)) {
            filterChain.doFilter(request, response);
            return;
        }

        // API key mancante o errato => blocchiamo la richiesta
        if (apiKey == null || !apiKey.equals("1234")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "API Key non valida o mancante");
            return;
        }

        filterChain.doFilter(request, response);
    }
}

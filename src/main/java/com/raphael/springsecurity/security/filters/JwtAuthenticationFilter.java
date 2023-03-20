package com.raphael.springsecurity.security.filters;

import com.raphael.springsecurity.security.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsServic
            ;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        // TODO: 1. extract the header which contains the bearer token from the request
        final String authHeader = request.getHeader("Authorization");

        // TODO: 2. Check if the token is present in the header
        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        // TODO: If yes, extract the token from the header
        final String token = authHeader.substring(7);

        // TODO: Extract the user email from the token
        String userEmail = jwtService.getUsername(token);
        // TODO: Get the authentication status of the user in the SecurityContextHolder
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (userEmail != null && auth == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
        // TODO: Check if the token is valid
            if (jwtService.isTokenValid(token, userDetails)){
                // TODO: If yes,
                // TODO: 1. Create an object of UserNamePasswordAuthenticationToken
                // TODO: 2. Update the SecurityContextHolder
                // TODO: 3. Pass it to the ne

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                  userDetails,
                  null,
                  userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            // TODO:
            filterChain.doFilter();
        }



    }
}

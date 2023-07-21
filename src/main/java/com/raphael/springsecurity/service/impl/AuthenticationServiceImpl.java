package com.raphael.springsecurity.service.impl;

import com.raphael.springsecurity.data.model.User;
import com.raphael.springsecurity.data.repository.UserRepository;
import com.raphael.springsecurity.dto.request.AuthenticationRequest;
import com.raphael.springsecurity.dto.response.AuthenticationResponse;
import com.raphael.springsecurity.exception.IncorrectEmailOrPasswordException;
import com.raphael.springsecurity.exception.UserNotFoundException;
import com.raphael.springsecurity.security.service.JwtService;
import com.raphael.springsecurity.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public ResponseEntity<AuthenticationResponse> login(AuthenticationRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new UserNotFoundException("User with email " + loginRequest.getEmail() + " not found"));
        if (!user.getPassword().equals(loginRequest.getPassword())) throw new IncorrectEmailOrPasswordException("email or password Incorrect");
       // Generate jwt token
        var jwtToken = jwtService.generateToken(user.getEmail());


        AuthenticationResponse authenticationResponse = AuthenticationResponse
                .builder()
                .accessToken(null)
                .build();
        return null;
    }

    @Override
    public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {
        return null;
    }
}

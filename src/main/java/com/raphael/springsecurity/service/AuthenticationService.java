package com.raphael.springsecurity.service;

import com.raphael.springsecurity.dto.request.AuthenticationRequest;
import com.raphael.springsecurity.dto.response.AuthenticationResponse;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {

    ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest);

    ResponseEntity<AuthenticationResponse> login(AuthenticationRequest authenticationRequest);
}

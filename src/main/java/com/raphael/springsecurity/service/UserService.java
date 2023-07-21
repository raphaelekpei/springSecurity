package com.raphael.springsecurity.service;

import com.raphael.springsecurity.data.model.User;
import com.raphael.springsecurity.dto.request.RegisterRequest;
import com.raphael.springsecurity.dto.response.RegistrationResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<RegistrationResponse> register(RegisterRequest registerRequest);

    User getUserByEmail(String email);
}

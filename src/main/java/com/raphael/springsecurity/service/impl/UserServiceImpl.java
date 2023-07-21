package com.raphael.springsecurity.service.impl;

import com.raphael.springsecurity.constant.Role;
import com.raphael.springsecurity.data.model.User;
import com.raphael.springsecurity.data.repository.UserRepository;
import com.raphael.springsecurity.dto.request.RegisterRequest;
import com.raphael.springsecurity.dto.response.RegistrationResponse;
import com.raphael.springsecurity.exception.UserNotFoundException;
import com.raphael.springsecurity.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public ResponseEntity<RegistrationResponse> register(RegisterRequest registerRequest) {
        User existingUser = userRepository.findByEmail(registerRequest.getEmail()).orElseThrow(() -> new UserNotFoundException("User does not exist"));

        var newUser = User.builder()
                .firstName(registerRequest.getFirstname())
                .lastName(registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER) // TODO: COME BACK TO THIS - how to assign roles
                .build();

        var savedUser = userRepository.save(newUser);


        RegistrationResponse registrationResponse = RegistrationResponse
                .builder()
                .message("Registration was successful")
                .build();

        return ResponseEntity.ok(registrationResponse);

    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
}

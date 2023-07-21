package com.raphael.springsecurity.controller;


import com.raphael.springsecurity.dto.request.RegisterRequest;
import com.raphael.springsecurity.dto.response.RegistrationResponse;
import com.raphael.springsecurity.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> register(@RequestBody RegisterRequest registerRequest) {
        return userService.register(registerRequest);
    }


}

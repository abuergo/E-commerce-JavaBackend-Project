package com.finalproject.Ecommerce.controller;

import com.finalproject.Ecommerce.model.exceptions.ApiRestException;
import com.finalproject.Ecommerce.model.request.UserRequest;
import com.finalproject.Ecommerce.model.response.UserResponse;
import com.finalproject.Ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public UserResponse login(@RequestParam String email, @RequestParam String password) throws ApiRestException{
        return userService.login(email, password);
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody UserRequest request) throws ApiRestException{
        return userService.register(request);
    }




}

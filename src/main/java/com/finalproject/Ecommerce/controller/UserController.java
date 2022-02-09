package com.finalproject.Ecommerce.controller;

import com.finalproject.Ecommerce.model.response.UserResponse;
import com.finalproject.Ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserResponse login(@RequestParam String email, @RequestParam String password){
        return userService.login(email,password);
    }




}

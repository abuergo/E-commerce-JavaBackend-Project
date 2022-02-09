package com.finalproject.Ecommerce.service.implementation;

import com.finalproject.Ecommerce.model.request.UserRequest;
import com.finalproject.Ecommerce.model.response.UserResponse;
import com.finalproject.Ecommerce.repository.UserRepository;
import com.finalproject.Ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public UserResponse login(String email, String password) {
        return null;
    }

    @Override
    public UserResponse register(UserRequest request) {
        return null;
    }
}

package com.finalproject.Ecommerce.service;

import com.finalproject.Ecommerce.model.request.UserRequest;
import com.finalproject.Ecommerce.model.response.UserResponse;

public interface UserService {
    UserResponse login(String email, String password);
    UserResponse register(UserRequest request);

}

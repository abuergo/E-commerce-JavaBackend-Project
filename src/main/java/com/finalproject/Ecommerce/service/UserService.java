package com.finalproject.Ecommerce.service;

import com.finalproject.Ecommerce.model.exceptions.ApiRestException;
import com.finalproject.Ecommerce.model.request.UserRequest;
import com.finalproject.Ecommerce.model.response.UserResponse;

public interface UserService {
    UserResponse login(String email, String password) throws ApiRestException;
    UserResponse register(UserRequest request) throws ApiRestException;

}

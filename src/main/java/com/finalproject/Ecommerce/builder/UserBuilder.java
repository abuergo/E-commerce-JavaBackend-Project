package com.finalproject.Ecommerce.builder;

import com.finalproject.Ecommerce.model.document.User;
import com.finalproject.Ecommerce.model.request.UserRequest;
import com.finalproject.Ecommerce.model.response.UserResponse;

public class UserBuilder {
    public static User requestToDocument(UserRequest request){
        return User.builder().
                username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .name(request.getName())
                .phone(request.getPhone())
                .build();
    }

    public static UserResponse documentToResponse(User user){
        return UserResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

}

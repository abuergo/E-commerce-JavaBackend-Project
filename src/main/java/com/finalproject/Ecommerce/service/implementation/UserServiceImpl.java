package com.finalproject.Ecommerce.service.implementation;

import com.finalproject.Ecommerce.builder.UserBuilder;
import com.finalproject.Ecommerce.cache.CacheClient;
import com.finalproject.Ecommerce.model.document.User;
import com.finalproject.Ecommerce.model.exceptions.ApiRestException;
import com.finalproject.Ecommerce.model.request.UserRequest;
import com.finalproject.Ecommerce.model.response.UserResponse;
import com.finalproject.Ecommerce.repository.UserRepository;
import com.finalproject.Ecommerce.security.JwtProvider;
import com.finalproject.Ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CacheClient<User> cache;
    private final JwtProvider jwtProvider;

    @Override
    public UserResponse login(String email, String password) throws ApiRestException {
        var existingUserInRedis = cache.recover(email, User.class);
        if(Objects.isNull(existingUserInRedis)){ // user does not exist in redis but exists on mongo database
            var existingUser = getUserWithEmailAndPassword(email, password); // if this is not the case then it finishes with an exception due to the user does not exist anywhere
            saveUserInCache(existingUser);
            return generateAndRespondUserWithToken(email); // user was on mongodb so we return the token
        }
        if(!Objects.equals(existingUserInRedis.getPassword(), password)){ // user exists in redis but password is invalid
            throw ApiRestException.builder().message("Invalid user or password").build();
        }
        return generateAndRespondUserWithToken(email); // user exists in redis and email and psw are ok
    }


    @Override
    public UserResponse register(UserRequest request) throws ApiRestException {
        validateUser(request);
        var user = userRepository.save(UserBuilder.requestToDocument(request));
        saveUserInCache(UserBuilder.requestToDocument(request));
        return UserBuilder.documentToResponse(user);
    }

    void validateUser(UserRequest request) throws ApiRestException{
        var user = getByUsername(request.getUsername());
        if(user != null){
            throw ApiRestException.builder().message("User already exists").build();
        }
        user = userRepository.findByEmail(request.getEmail());
        if(user != null){
            throw ApiRestException.builder().message("Email already registered").build();
        }
    }

    private User getByUsername(String username){
        return userRepository.findByUsername(username);
    }

    private void saveUserInCache(User user){
        cache.save(user.getEmail(), user); // user registered is saved in Redis with an email key
    }

    private User getUserWithEmailAndPassword(String email, String password) throws ApiRestException{
        var userEmailFound = userRepository.findByEmail(email);
        if(userEmailFound == null || !Objects.equals(password, userEmailFound.getPassword())){
            throw ApiRestException.builder().message("Invalid user or password").build();
        }
        return userEmailFound;
    }

    private UserResponse generateAndRespondUserWithToken(String email){
        var token = jwtProvider.getJWTToken(email);
        return UserResponse.builder().email(email).token(token).build();
    }
}

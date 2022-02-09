package com.finalproject.Ecommerce.model.request;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public class UserRequest {

    @NotBlank
    private String username;
    @NotBlank
    @Length(min=4, max=10)
    private String password;
    @Email
    private String email;
    @NotBlank
    private String name;
    private String phone;

}

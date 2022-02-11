package com.finalproject.Ecommerce.model.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CartRequest {
    @Email
    private String email;
    @NotNull
    private String deliveryAddress;
}

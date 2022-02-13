package com.finalproject.Ecommerce.model.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class CartResponse {
    private Integer orderNumber;
    private List<CartItemResponse> products;
    private String email;
    private String status;
    private LocalDateTime creationDate;
}

package com.finalproject.Ecommerce.model.response;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class CartItemResponse {
    private String category;
    private String code;
    private Double price;
    private String description;
    private Integer quantity;
}

package com.finalproject.Ecommerce.model.document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItem {
    private String code;
    private Integer quantity;
}

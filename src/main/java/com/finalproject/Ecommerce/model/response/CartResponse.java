package com.finalproject.Ecommerce.model.response;

import com.finalproject.Ecommerce.model.document.CartItem;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class CartResponse {
    private Integer orderNumber;
    private List<CartItem> products;
    private String email;
    private String status;
    private LocalDateTime creationDate;
}

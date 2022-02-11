package com.finalproject.Ecommerce.model.document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Document("cart")
public class Cart {
    @Id
    private String id;
    private String email;
    private List<CartItem> products;
    private String deliveryAddress;
    private Integer orderNumber;
    private String status;
    private LocalDateTime creationDate;
}

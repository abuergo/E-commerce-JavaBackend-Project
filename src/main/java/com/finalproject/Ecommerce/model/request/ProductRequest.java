package com.finalproject.Ecommerce.model.request;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class ProductRequest {
    // TO DO : add more validations
    @NotNull
    private Double price;
    private String description;
    private String category;
    private String code;


}

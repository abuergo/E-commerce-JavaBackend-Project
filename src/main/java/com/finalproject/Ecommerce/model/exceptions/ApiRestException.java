package com.finalproject.Ecommerce.model.exceptions;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
public class ApiRestException extends Exception{
    private String code;
    private String message;

    public ApiRestException(String message){
        super(message);
    }

    public ApiRestException(String code, String message){
        super(message);
        this.code = code;
    }
}

package com.finalproject.Ecommerce.model.exceptions;

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

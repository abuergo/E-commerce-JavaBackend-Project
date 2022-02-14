package com.finalproject.Ecommerce.model.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class MessageErrorHandle {

    @ResponseBody
    @ExceptionHandler(ApiRestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorMessage messageErrorHandle(ApiRestException ex){
        log.error(String.valueOf(ex));
        return ErrorMessage.of(ex.getCode(), ex.getMessage());
    }
}

package com.finalproject.Ecommerce.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class EmailInterceptor {
    @After("execution(* com.finalproject.Ecommerce.service.implementation.EmailServiceImpl.*(..))")
    void afterAnyMethod(){
        log.info("AOP: the after advice is executed after executing an Email Service method");
    }

    @AfterThrowing("execution(* com.finalproject.Ecommerce.service.implementation.EmailServiceImpl.*(..))")
    void afterAnyThrowingMethod(){
        log.error("AOP: the after advice is executed after encountering an exception during the execution of an Email Service method");
    }
}

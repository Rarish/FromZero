package com.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.response.ResponseMessage;

import javax.annotation.Priority;
import java.lang.reflect.UndeclaredThrowableException;

@RestControllerAdvice
@Priority(1)
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = JwtException.class)
    public ResponseMessage handle(Throwable e){
        if(e instanceof JwtException){
            return ResponseMessage.fail(e.getMessage());
        }
        if (e instanceof UndeclaredThrowableException) {
            e = ((UndeclaredThrowableException) e).getUndeclaredThrowable();
        }
        return ResponseMessage.fail(e.getMessage());
    }
}

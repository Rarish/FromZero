package com.controller;

import com.exception.JwtException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.response.ResponseMessage;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ErrorController {
    @PostMapping("/JwtError")
    public ResponseMessage error(HttpServletRequest request) throws Exception{
        throw (JwtException) request.getAttribute("exception");
    }
}

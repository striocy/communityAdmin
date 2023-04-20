package com.cy.myException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class WebErrorController {
    @ExceptionHandler(ValueInvalidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> handleValueInvalid(ValueInvalidException e){
        Map<String,Object> result=new HashMap<>();
        result.put("value",e.valueName);
        result.put("message",e.msg);
        return result;
    }
}

package com.woniu.exception;


import com.woniu.commons.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //定义不同方法处理不同类型的异常
    @ExceptionHandler({BindException.class})
    public JsonResult handlerBindException(Exception e){
        e.printStackTrace();
        return new JsonResult(505,"参数异常",null);
    }

    @ExceptionHandler({Exception.class})
    public JsonResult handlerException(Exception e){
        e.printStackTrace();
        return new JsonResult(500,"服务器异常",null);
    }

}


package com.woniu.exception;


import com.woniu.utils.JSONResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
	//定义不同的方法处理不同类型的异常
	@ExceptionHandler({NumberNotFoundException.class})
	public JSONResult handlerException(Exception e) {
		e.printStackTrace();
		return new JSONResult("500",e.getMessage(),null,null);
	}
}

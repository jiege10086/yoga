package com.woniu.exception;


import com.netflix.config.validation.ValidationException;

import com.woniu.utils.JSONResult;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;




@RestControllerAdvice
public class GolbalExcpetionHandler {
		@ExceptionHandler({BindException.class, ValidationException.class})
		public JSONResult handlerBindException(Exception e) {
			e.printStackTrace();
			return new JSONResult("500","参数异常",null,null);
		}

	@ExceptionHandler({VenExcpetion.class})
	public JSONResult userExcpetion(Exception e) {
		e.printStackTrace();
		return new JSONResult("500","用户不存在",null,null);
	}
	@ExceptionHandler({Exception.class})
	public JSONResult handlerException(Exception e) {
		e.printStackTrace();
		return new JSONResult("500","服务器异常",null,null);
	}

}

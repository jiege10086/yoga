package com.woniu.exception;

public class NumberNotFoundException extends Exception{
	 //定义无参构造方法
    public NumberNotFoundException() {
        super();
    }

    //定义有参构造方法
    public NumberNotFoundException(String message) {
        super(message);
    }
}

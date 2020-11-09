package com.woniu.test;

import com.woniu.utils.JSONResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


public class Test {
    @GetMapping
    public JSONResult test(int id){
        System.out.println(1);
        return new JSONResult("200","wuhu",null,id);
    }

}

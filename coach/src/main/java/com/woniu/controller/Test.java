package com.woniu.controller;

import com.woniu.utils.JSONResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Test {
    @GetMapping
    public JSONResult test(int id){
        return new JSONResult("200","wuhu",null,id);
    }
}

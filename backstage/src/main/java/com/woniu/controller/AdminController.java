package com.woniu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.domain.Admin;
import com.woniu.doparam.AdminParam;
import com.woniu.dto.AdminToken;
import com.woniu.service.AdminService;
import com.woniu.service.TVenuesService;
import com.woniu.utils.JSONResult;
import com.woniu.utils.JwtUtils;
import com.woniu.utils.MD5Util;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zly
 * @since 2020-11-11
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;


    /*
    * 管理员登录
    * */
    @GetMapping("/admninLogin")
    public JSONResult login(HttpServletResponse response, AdminParam adminParam) throws Throwable {
        QueryWrapper<Admin> adminQueryWrapper = new QueryWrapper<>();
        adminQueryWrapper.eq("account",adminParam.getAccount()).eq("password", MD5Util.MD5EncodeUtf8(adminParam.getPassword()));
        Admin one = adminService.getOne(adminQueryWrapper);
        AdminToken build = AdminToken.builder().id(one.getId() + "").role(3 + "").build();
        String token = JwtUtils.createToken(build);

        response.setHeader("X-token", token);
        System.out.println(token);
        return new JSONResult("200","success",null,one);
    }


}


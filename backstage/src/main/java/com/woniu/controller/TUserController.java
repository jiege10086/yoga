package com.woniu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.domain.TUser;
import com.woniu.doparam.UserParam;
import com.woniu.service.TUserService;
import com.woniu.utils.JSONResult;
import com.woniu.utils.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.xml.namespace.QName;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zly
 * @since 2020-11-11
 */
@RestController
@RequestMapping("/tUser")
public class TUserController {


    @Resource
    private TUserService tUserService;

    @GetMapping("/selectUser")
    public JSONResult selectUsers(UserParam u)throws Exception{

            Page<TUser> userPage = new Page<>(u.getPageSize(),u.getPageNum());
            QueryWrapper<TUser> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("u_level",u.getULevel());
            Page<TUser> page = tUserService.page(userPage,userQueryWrapper);
            return new JSONResult("200","success",null,page);

    }

    @RequestMapping("updateUserLevel")
    public JSONResult updateUserLevel(UserParam u)throws Exception{
        TUser user = tUserService.getById(u.getUId());
        if(user!=null){
            if(user.getuMoney()>1000&&user.getuMoney()<5000){
                UpdateWrapper<TUser> userUpdateWrapper = new UpdateWrapper<>();
                userUpdateWrapper.eq("u_id",u.getUId()).set("u_level",2);
                tUserService.update(userUpdateWrapper);
            }else if(user.getuMoney()>5000){
                UpdateWrapper<TUser> userUpdateWrapper = new UpdateWrapper<>();
                userUpdateWrapper.eq("u_id",u.getUId()).set("u_level",3);
                tUserService.update(userUpdateWrapper);
            }
        }
        return new JSONResult("200","success",null,null);
    }

    @PutMapping("/updateUser")
    public JSONResult updateUser(UserParam u)throws Exception{
        UpdateWrapper<TUser> userUpdateWrapper = new UpdateWrapper<>();
            userUpdateWrapper.eq("u_id",u.getUId()).set("u_phone",u.getUPhone())
                    .set("u_e_mail",u.getUEMail()).set("u_name",u.getUName())
                    .set("u_password", MD5Util.MD5EncodeUtf8(u.getUPassword()));
            tUserService.update(userUpdateWrapper);
        return new JSONResult("200","success",null,null);
    }

    @PutMapping("/removeUser")
    public JSONResult removeUser(UserParam u)throws Exception{
        UpdateWrapper<TUser> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("u_id",u.getUId()).set("u_status",1);
        tUserService.update(userUpdateWrapper);
        return new JSONResult("200","success",null,null);
    }
}


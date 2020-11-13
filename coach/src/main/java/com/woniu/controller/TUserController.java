package com.woniu.controller;


import com.github.pagehelper.PageInfo;
import com.woniu.dto.CoaDtoToken;
import com.woniu.dto.CoaUserDto;
import com.woniu.exception.NumberNotFoundException;
import com.woniu.service.TCoachService;
import com.woniu.service.TUserService;
import com.woniu.utils.JSONResult;
import com.woniu.utils.JwtUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jiejiang
 * @since 2020-11-07
 */
@RestController
@RequestMapping("/tUser")
public class TUserController {
    @Resource
    private TUserService tUserService;

    //教练查看自己学员接口
    @RequestMapping("/selectUserByCoa")
    public JSONResult selectUserByCoa(@RequestHeader("X-token")String token,int pageSize,int pageIndex) throws Throwable {
        if(token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }
        CoaDtoToken coach = JwtUtils.parseToken(token, CoaDtoToken.class);
        PageInfo<CoaUserDto> coaUserDtoPageInfo = tUserService.selectUserByCoa(Integer.parseInt(coach.getCoaId()), pageSize, pageIndex);
        return new JSONResult("200","查询成功",null,coaUserDtoPageInfo);
    }

    //用户查看附近的学员
    @RequestMapping("/selectCoaByAddress")
    public JSONResult selectCoaByAddress(double longitude,double latitude,double fanwei){
        return new JSONResult("200","查询成功", tUserService.selectCoaByAddress(longitude,latitude,fanwei),null);
    }

    //教练新增用户
    @RequestMapping("/insertCoaMyUser")
    public JSONResult insertCoaMyUser(@RequestHeader("X-token")String token,int userId) throws Throwable {
        if(token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }
        CoaDtoToken coach = JwtUtils.parseToken(token, CoaDtoToken.class);
        tUserService.insertCoaMyUser(Integer.parseInt(coach.getCoaId()),userId);
        return new JSONResult("200","新增成功",null,null);
    }


}


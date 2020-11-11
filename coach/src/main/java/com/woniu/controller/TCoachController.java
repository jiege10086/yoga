package com.woniu.controller;



import com.woniu.domain.TCoach;
import com.woniu.dto.CoaAddressDtoList;
import com.woniu.dto.CoaDtoToken;
import com.woniu.exception.NumberNotFoundException;
import com.woniu.param.CoaRegister;
import com.woniu.service.impl.TCoachServiceImpl;
import com.woniu.utils.JSONResult;
import com.woniu.utils.JwtUtils;
import com.woniu.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jiejiang
 * @since 2020-11-07
 */
@RestController
@RequestMapping("/tCoach")
public class TCoachController {
    @Autowired
    private TCoachServiceImpl tCoachService;

    @Autowired
    private RedisTemplate<String, Object> rt;

    @RequestMapping("/coaRegister")
    public JSONResult coaRegister(@RequestBody CoaRegister coaRegister) throws Exception{
        tCoachService.insertCoach(coaRegister);
        return new JSONResult("200","注册成功",null,null);
    }

    @GetMapping
    public void selectCoachById(int coaId) throws Exception{
        tCoachService.selectCoachById();
    }

    //教练登录
    @RequestMapping("/coaLogin")
    public JSONResult coaLogin(HttpServletResponse response, String name, String password,Double jingdu,Double weidu,Double fanwei) throws Throwable {
        TCoach tCoach =tCoachService.CoachLogin(name, MD5Util.MD5EncodeUtf8(password));
        boolean flag=true;
        if(tCoach.getIdcard()==null){
            flag=false;
        }
        CoaDtoToken coaDtoToken = new CoaDtoToken();
        coaDtoToken.setCoaId(tCoach.getCoaId()+"");
        coaDtoToken.setRole(1+"");
        String token = JwtUtils.createToken(coaDtoToken);
        response.setHeader("X-token",token);
        //删除上次登录地址
        rt.opsForGeo().remove("coaAddress",tCoach.getCoaId());
        //保存这次登录地址
        rt.opsForGeo().add("coaAddress",new Point(jingdu, weidu),tCoach.getCoaId());
        return new JSONResult("200","登陆成功",null,flag);
    }

    //教练信息完善
    @RequestMapping("/coaDate")
    public JSONResult updateCoa(@RequestHeader("X-token") String token,TCoach tCoach) throws Throwable {
        if(token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }
        CoaDtoToken coach = JwtUtils.parseToken(token, CoaDtoToken.class);
        tCoach.setCoaId(Integer.parseInt(coach.getCoaId()));
        tCoachService.updateCoach(tCoach);
        return  new JSONResult("200","完善成功",null,null);
    }

    //获得新密码
    @RequestMapping("/newPassword")
    public JSONResult newPassword(String name) throws Exception {
        return new JSONResult("200","新密码30分钟有效",null, tCoachService.newPassword(name));
    }

    //新密码登录
    @RequestMapping("/newPasswordLogin")
    public JSONResult newPasswordLogin(String name,String password) throws Exception {
        tCoachService.newPasswordLogin(name,password);
        return new JSONResult("200","验证成功",null,null);
    }

    //取现
    @RequestMapping("/coaGetMoney")
    public JSONResult coaGetMoney(@RequestHeader("X-token") String token,Double money,int banecard) throws Throwable {
        if(token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }
        CoaDtoToken coach = JwtUtils.parseToken(token, CoaDtoToken.class);
        tCoachService.coaGetMoney(banecard,money,Integer.parseInt(coach.getCoaId()));
        return new JSONResult("200","取现成功",null,null);
    }

}


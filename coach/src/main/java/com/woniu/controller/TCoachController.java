package com.woniu.controller;



import com.woniu.domain.TCoach;
import com.woniu.dto.CoaAddressDtoList;
import com.woniu.dto.CoaDtoToken;
import com.woniu.exception.NumberNotFoundException;
import com.woniu.fen.DynamicFen;
import com.woniu.param.CoaRegister;
import com.woniu.service.impl.TCoachServiceImpl;
import com.woniu.utils.JSONResult;
import com.woniu.utils.JwtUtils;
import com.woniu.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;


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

    @Resource
    private DynamicFen dynamicFen;

    //教练注册
    @RequestMapping("/coaRegister")
    public JSONResult coaRegister(@RequestBody CoaRegister coaRegister) throws Exception{
        tCoachService.insertCoach(coaRegister);
        return new JSONResult("200","注册成功",null,null);
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
        coaDtoToken.setCoaName(tCoach.getCoaName());
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
    public JSONResult newPassword(String name,int status) throws Exception {
        return new JSONResult("200","新密码30分钟有效",null, tCoachService.newPassword(name,status));
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

    //教练添加我的关注
    @RequestMapping("/insertAttention")
    public JSONResult insertAttention(@RequestHeader("X-token")String token,int peoId,int role) throws Throwable {
        if(token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }
        CoaDtoToken coach = JwtUtils.parseToken(token, CoaDtoToken.class);
        tCoachService.insertAttention(Integer.parseInt(coach.getCoaId()),peoId,role);
        return new JSONResult("200","添加成功",null,null);
    }

    //教练查看我的关注
    @RequestMapping("/selectDynMyId")
    public JSONResult selectDynMyId(@RequestHeader("X-token")String token, int pageSize, int pageIndex) throws Throwable {
        if(token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }
        CoaDtoToken coach = JwtUtils.parseToken(token, CoaDtoToken.class);
        JSONResult jsonResult = dynamicFen.selectDynMyId(Integer.parseInt(coach.getCoaId()), pageSize, pageIndex, 1);
        return jsonResult;
    }
}


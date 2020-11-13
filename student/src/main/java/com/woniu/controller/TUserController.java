package com.woniu.controller;


import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.woniu.commons.JsonResult;
import com.woniu.domain.*;
import com.woniu.dto.TUserDtoToken;
import com.woniu.exception.NumberNotFoundException;
import com.woniu.service.*;
import com.woniu.utils.JSONResult;
import com.woniu.utils.JwtUtils;
import com.woniu.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Lx
 * @since 2020-11-07
 */
@RestController
@RequestMapping("/student")
public class TUserController {
    @Autowired
    private CoachTradingService coachTradingService;
    @Resource
    private RedisTemplate<String, Object> rt;

    @Autowired
    private TUserService tUserService;

    @Autowired
    private TOrderService tOrderService;

    @Autowired
    private TConsumptionService tConsumptionService;

    @Autowired
    private TCoachService tCoachService;

    @Autowired
    private TVenuesService tVenuesService;

    @Autowired
    private TMessageService tMessageService;
    /**
     * 用户手机注册
     * @param uPhone
     * @param uEMail
     * @return
     */
    @GetMapping("/searchUser")
    public JsonResult searchUser(Integer uPhone,String uEMail) {
        if (uPhone != null) {
            TUser tUser = tUserService.searchUserByPhone(uPhone);
            if (tUser != null) {
                return new JsonResult(200, "success", tUser,null);
            } else {
                return new JsonResult(400, "fail", null,null);
            }
        } else if (uEMail != null) {
            TUser tUser = tUserService.searchUserByEmail(uEMail);
            if (tUser != null) {
                return new JsonResult(200, "success", tUser,null);
            } else {
                return new JsonResult(400, "fail", null,null);
            }
        }
        return null;
    }

    /**
     * 用户邮箱注册
     * @param uPhone
     * @param uEMail
     * @param uPassword
     * @return
     */
    @PostMapping("/regist")
    public JsonResult registUser(Integer uPhone,String uEMail,String uPassword){
            if(uPhone!=null) {
                TUser tUser = tUserService.searchUserByPhone(uPhone);
                if(tUser==null){
                    tUserService.registUserByPhone(uPhone,uPassword);
                }
            }else if(uEMail != null){
                TUser tUser = tUserService.searchUserByEmail(uEMail);
                if(tUser==null) {
                    tUserService.registUserByEmamil(uEMail, uPassword);
                }
            }
               return new JsonResult(200,"success",null,null);
            }
    /**
     * 密码找回
     */
   //获得新密码
    @RequestMapping("/newPassword")
    public JSONResult newPassword(String name) throws Exception {
        return new JSONResult("200","新密码30分钟有效",null, tUserService.newPassword(name));
    }


    //新密码登录
    @RequestMapping("/newPasswordLogin")
    public JSONResult newPasswordLogin(String uName,String password) throws Exception {
        tUserService.newPasswordLogin(uName,password);
        return new JSONResult("200","验证成功",null,null);
    }


    /**
     * 用户的登录
     */
    @GetMapping("/userLogin")
    public JsonResult userLogin(HttpServletResponse response, Integer uPhone, String uEMail, String uPassword,Double jingdu,Double weidu,Double fanwei) throws Throwable {
        TUserDtoToken tUserDtoToken=null;
        if (uPhone != null && uPassword != null) {
            TUser tUser = tUserService.userLoginByPhone(uPhone, MD5Util.MD5EncodeUtf8(uPassword));
            tUserDtoToken = new TUserDtoToken();
            tUserDtoToken.setCoaId(tUser.getUId()+"");
            tUserDtoToken.setRole(1+"");
            String token = JwtUtils.createToken(tUserDtoToken);
            response.setHeader("X-token",token);
            //删除上次登录地址
            rt.opsForGeo().remove("coaAddress",tUser.getUId());
            //保存这次登录地址
            rt.opsForGeo().add("coaAddress",new Point(jingdu, weidu),tUser.getUId());
            return new JsonResult(200,"success",tUser,null);
        } else {
            if (uEMail != null && uPassword != null) {
                TUser tUser = tUserService.userLoginByEmail(uEMail, MD5Util.MD5EncodeUtf8(uPassword));
                tUserDtoToken = new TUserDtoToken();
                tUserDtoToken.setCoaId(tUser.getUId()+"");
                tUserDtoToken.setRole(1+"");
                String token = JwtUtils.createToken(tUserDtoToken);
                response.setHeader("X-token",token);
                //删除上次登录地址
                rt.opsForGeo().remove("coaAddress",tUser.getUId());
                //保存这次登录地址
                rt.opsForGeo().add("coaAddress",new Point(jingdu, weidu),tUser.getUId());
                return new JsonResult(200,"success",tUser,null);
            } else {
                return new JsonResult(205, "输入手机或密码有误", null,null);
            }
        }

    }


    /**
     * 信息完善接口
     */
    @PutMapping("/perfectUserInfo")
    public JsonResult perfectUserInfo(@RequestHeader("X-token") String token,Integer uPhone,String uEMail,Integer uShowStatus,String uHeadPortrait,String uName,String uAddress,String uTruename,Integer uIdcard,Integer uQq) throws NumberNotFoundException {
        if(token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }
        TUser tUser = tUserService.searchUserByPhone(uPhone);
        if(tUser==null){
            tUserService.perfectUserInfoByUEmail(uPhone,uEMail,uShowStatus,uHeadPortrait,uName,uAddress,uTruename,uIdcard,uQq);
        }else {
            tUserService.perfectUserInfoByUphone(uPhone,uEMail,uShowStatus,uHeadPortrait,uName,uAddress,uTruename,uIdcard,uQq);
        }
            return new JsonResult(200,"success",null,null);
    }
    /**
     * 登录进来通过电话或邮箱查询id
     */
    @GetMapping("/searchUserId")
    public JsonResult searchUserId(Integer uPhone,String uEMail) {
        TUser tUser=null;
        if (uPhone != null) {
             tUser= tUserService.searchUserByPhone(uPhone);
        }
        else if(uEMail!=null){
            tUser = tUserService.searchUserByEmail(uEMail);
        }
        return new JsonResult(200,"success",tUser,null);
    }

    /**
     * 学员充值
     */
    @PutMapping("/addUserMoney")
    public JsonResult addUserMoney(@RequestHeader("X-token") String token,Integer uId,double uMoney) throws NumberNotFoundException {
        if(token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }
        TUser tUser = tUserService.searchUserById(uId);
        System.out.println(tUser);
        System.out.println("......RMB:"+tUser.getUMoney());
        double uMoney1 = tUser.getUMoney();
        double uTotalMoney=uMoney+uMoney1;
        int status=0;//充值
        String detail="用户充值";
        int peoRole=0;
        tConsumptionService.addUserMoney(uId,uMoney,status,detail,peoRole);
        tUserService.addUserMoney(uId,uTotalMoney);
        return new JsonResult(200,"success",null,null);
    }

    /**
     * 查看我的钱包
     */
    @GetMapping("/showUserMoney")
    public JsonResult showUserMoney(@RequestHeader("X-token") String token,Integer uPhone) throws NumberNotFoundException {
        if(token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }
        TUser tUser = tUserService.searchUserByPhone(uPhone);
        Double uMoney = tUser.getUMoney();
        return new JsonResult(200,"success",tUser,null);
    }

    /**
     * 查看用户订单
     */
    @GetMapping("/showUserOrder")
    public JsonResult showUserOrder(@RequestHeader("X-token") String token,Integer uPhone) throws NumberNotFoundException {
        if(token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }
        TUser tUser = tUserService.searchUserByPhone(uPhone);
        Integer uId = tUser.getUId();
        List<TOrder> tOrders = tOrderService.showUserOrder(uId);
        return new JsonResult(200,"success",null,tOrders);
    }

    /**
     * 用户发起订单
     */
    @PutMapping("/addUserOrder")
    public JsonResult addUserOrder(@RequestHeader("X-token") String token,Integer coaId,Integer uId,String coaName,String uName,String reservationTime,int uPhone,int status) throws NumberNotFoundException {
        if(token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }
        status=0;
        tOrderService.addUserOrder(coaId,uId,coaName,uName,reservationTime,uPhone,status);
        return new JsonResult(200,"success",null,null);
    }
    /**
     * 用户确认订单
     */
    @PutMapping("/userConfirmOrder")
    public JsonResult userConfirmOrder(@RequestHeader("X-token") String token,Integer ordId) throws NumberNotFoundException {
        if(token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }
        //根据id查出当前哪条订单记录
        TOrder tOrder = tOrderService.selectOrder(ordId);
        Integer coaId = tOrder.getCoaId();
        String coaName = tOrder.getCoaName();
        Double uMoney = tOrder.getMoney();
        Integer uId = tOrder.getUId();
        //确认订单状态改变
        tOrderService.updateOrderStatus(ordId);
        //确认订单消费记录加一条
        int peoRole=0;
        String detail="确认订单";
        int status=1;
        tConsumptionService.addUserMoney(uId,uMoney,status,detail,peoRole);
        //确认订单用户余额减少
        TUser tUser = tUserService.searchUserById(uId);
        Double uMoney1 = tUser.getUMoney();
        double money=uMoney1-uMoney;
        tUserService.updateUserMoney(uId,money);
        //增加教练成交量加一
        CoachTrading coachTrading = coachTradingService.selectByCoaId(coaId);
        if(coachTrading!=null){
            Integer clinch = coachTrading.getClinchSuccess();
            int clinchSuccess=clinch+1;
            coachTradingService.addCoaClich(coaId,clinchSuccess);
        }else {
            int clisuccess=1;
            coachTradingService.addNewCoaClich(coaId,coaName,clisuccess);
        }
        return new JsonResult(200,"success",null,null);
    }
    /**
     * 用户根据检索条件查出所有教练
     */
    @GetMapping("/selectAllCoach")
    public JsonResult selectAllCoach(){
        List<TCoach> tCoaches = tCoachService.selectAllCoach();
        return new JsonResult(200,"success",null,tCoaches);
    }

    /**
     * 用户根据检索条件查出所有场馆
     */
    @GetMapping("/selectAllVenues")
    public JsonResult selectAllVenues(){
        List<TVenues> tVenues = tVenuesService.selectAllVenues();
        return new JsonResult(200,"success",null,tVenues);
    }

    /**
     * 用户点击图文后查看教练详细信息
     */
    @GetMapping("/selectCoach")
    public JsonResult selectCoach(@RequestHeader("X-token") String token,Integer coaId) throws NumberNotFoundException {
        if(token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }
        TCoach tCoach = tCoachService.selectCoach(coaId);
        return new JsonResult(200,"success",tCoach,null);
    }

    /**
     * 用户点击图文后查看场馆详细信息
     */
    @GetMapping("/selectVenue")
    public JsonResult selectVenue(@RequestHeader("X-token") String token,Integer venId) throws NumberNotFoundException {
        if(token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }
        TVenues tVenues = tVenuesService.selectVenue(venId);
        return new JsonResult(200,"success",tVenues,null);
    }
    /**
     * 添加查看我的留言
     */
    public JsonResult showUserMessages(@RequestHeader("X-token") String token,Integer uid) throws NumberNotFoundException {
        if(token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }
        List<TMessage> tMessages = tMessageService.showUserMessages(uid);
        if(tMessages==null){
            return new JsonResult(400,"failed",null,null);
        }else {
            return new JsonResult(200,"success",tMessages,null);
        }
    }

    /**
     * 添加我的关注
     */
    @PutMapping("/addUserAttention")
    public JsonResult addUserAttention(@RequestHeader("X-token") String token,Integer userId,Integer coaId,Integer venId,Integer uId) throws NumberNotFoundException {
        if(token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }
        //先查出对应用户的关注列表有哪些
        TUser tUser = tUserService.searchUserById(userId);
        String uAttentionCoach = tUser.getUAttentionCoach();
        String uAttentionUser = tUser.getUAttentionUser();
        String uAttentionVenues = tUser.getUAttentionVenues();
        List parse=(List)JSON.parse(uAttentionCoach);
        List parse1=(List)JSON.parse(uAttentionUser);
        List parse2=(List)JSON.parse(uAttentionVenues);
        if(coaId!=0){
            parse.add(coaId);
            Gson gson = new Gson();
            String json = gson.toJson(parse);
            tUserService.updateUserAttention(userId,json);
        }
        if(venId!=0){
            parse2.add(venId);
            Gson gson = new Gson();
            String json = gson.toJson(parse2);
            tUserService.updateUserVenAttention(userId,json);
        }
        if(uId!=0){
            parse1.add(uId);
            Gson gson = new Gson();
            String json = gson.toJson(parse1);
            tUserService.updateUserUserAttention(userId,json);
        }
        return new JsonResult(200,"success",null,null);
    }

    /**
     * 学员查找附近的教练
     */
    @RequestMapping("/selectCoaByAddress")
    public JsonResult selectCoaByAddress(@RequestHeader("X-token") String token,double longitude, double latitude, double fanwei) throws NumberNotFoundException {
        if(token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }
        tUserService.selectCoaByAddress(longitude,latitude,fanwei);
        return new JsonResult(200,"sucess", null,null);
    }

    /**
     * 查看教练交易记录
     */
    @GetMapping("/selectCoaTradings")
    public JsonResult selectCoaTradings(Integer coaId){
        CoachTrading coachTrading = coachTradingService.selectCoaTradings(coaId);
        return new JsonResult(200,"success",coachTrading,null);
    }

    /**
     * 添加教练留言
     */
    @PostMapping("/addUserCommit")
    public JsonResult addUserCommit(@RequestHeader("X-token") String token,Integer uId, String detail, Integer acceptId) throws NumberNotFoundException {
        if(token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }
        TUser tUser = tUserService.searchUserById(uId);
        String sendName = tUser.getUName();
        tMessageService.addUserCommit(uId,detail,acceptId,sendName);
        return new JsonResult(200,"success",null,null);
    }

    /**
     * 检索教练查询
     */
    @GetMapping("/searchCoaches")
    public JsonResult searchCoaches(double price,String factions,int certificationStatus,int morningStatus,int afternoonStatus,int nightStatus){
        List<TCoach> tCoaches = tCoachService.searchCoaches(price, factions, certificationStatus, morningStatus, afternoonStatus, nightStatus);
        return new JsonResult(200,"success",null,tCoaches);
    }
    /**
     * 约私教流程-发起约私教流程
     */
    @PostMapping("/userSubscribe")
    public JsonResult userSubscribe(@RequestHeader("X-token") String token,Integer uId,Integer coaId,String reservationTime,int status,int uPhone) throws NumberNotFoundException {
        if(token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }
        TUser tUser = tUserService.searchUserById(uId);
        String uName = tUser.getUName();
        Double uMoney = tUser.getUMoney();
        uPhone = tUser.getUPhone();
        TCoach tCoach = tCoachService.selectCoach(coaId);
        String coaName = tCoach.getCoaName();
        Double coaMoney = tCoach.getCoaMoney();
        status=0;
        //判断余额是否充足
        if(uMoney>=coaMoney){
            //先根据用户教练判断该订单是否存在，已存在就覆盖，没存在就新增
            TOrder tOrder = tOrderService.selectUserOrder(uId, coaId);
            if(tOrder!=null){
                //执行更新操作
                tOrderService.updateuserSubscribe(uId,uName,coaId,coaName,reservationTime,status,uPhone);
            }else {
                //执行新增操作
                tOrderService.adduserSubscribe(uId,uName,coaId,coaName,reservationTime,status,uPhone);
            }
            return new JsonResult(200,"success",null,null);
        }else {
            return new JsonResult(205,"余额不足，请充值",null,null);
        }
    }
    /**
     * 用户确认订单后进行评价
     */
    public JsonResult commitCoach(@RequestHeader("X-token") String token,Integer coaId,Integer goodPraise,Integer generalPraise,Integer badPraise,Integer uId, String detail, Integer acceptId) throws NumberNotFoundException {
        if(token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }
        addUserCommit(token,uId,detail,acceptId);
        CoachTrading coachTrading = coachTradingService.selectByCoaId(coaId);
        Integer badPraise1 = coachTrading.getBadPraise();
        Integer generalPraise1 = coachTrading.getGeneralPraise();
        Integer goodPraise1 = coachTrading.getGoodPraise();
        if(generalPraise!=0){
            generalPraise1=generalPraise1+1;
            coachTradingService.commitCoach(coaId,goodPraise1,generalPraise1,badPraise1);
        }else if(badPraise!=0){
            badPraise1=badPraise1+1;
            coachTradingService.commitCoach(coaId,goodPraise1,generalPraise1,badPraise1);
        }
        else if(goodPraise!=0){
            goodPraise1=goodPraise1+1;
            coachTradingService.commitCoach(coaId,goodPraise1,generalPraise1,badPraise1);
        }
        return new JsonResult(200,"success",null,null);
    }

}


package com.woniu.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.woniu.commons.JsonResult;
import com.woniu.domain.TCoach;
import com.woniu.domain.TOrder;
import com.woniu.domain.TUser;
import com.woniu.domain.TVenues;
import com.woniu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    private TUserService tUserService;

    @Autowired
    private TOrderService tOrderService;

    @Autowired
    private TConsumptionService tConsumptionService;

    @Autowired
    private TCoachService tCoachService;

    @Autowired
    private TVenuesService tVenuesService;
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
//    @GetMapping("/retrievepwd")
//    public JsonResult retrievePwd(int uPhone,String uEmail) {
//        if (uPhone != 0) {
//            TUser tUser = tUserService.retrievePwdByPhone(uPhone);
//            if (tUser != null) {
//                return new JsonResult(200, "success", tUser);
//            } else {
//                return new JsonResult(400, "fail", null);
//            }
//        } else if (uEmail != null) {
//            TUser tUser = tUserService.retrievePwdByEmail(uEmail);
//            if (tUser != null) {
//                return new JsonResult(200, "success", tUser);
//            } else {
//                return new JsonResult(400, "fail", null);
//            }
//        }
//    }
    /**
     * 用户的登录
     */
    @GetMapping("/userLogin")
    public JsonResult userLogin(Integer uPhone,String uEMail,String uPassword) {
        if (uPhone != null && uPassword != null) {
            TUser tUser = tUserService.userLoginByPhone(uPhone, uPassword);
            return new JsonResult(200,"success",tUser,null);
        } else {
            if (uEMail != null && uPassword != null) {
                TUser tUser = tUserService.userLoginByEmail(uEMail, uPassword);
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
    public JsonResult perfectUserInfo(Integer uPhone,String uEMail,Integer uShowStatus,String uHeadPortrait,String uName,String uAddress,String uTruename,Integer uIdcard,Integer uQq){
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
    public JsonResult addUserMoney(Integer uId,double uMoney){
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
    public JsonResult showUserMoney(Integer uPhone){
        TUser tUser = tUserService.searchUserByPhone(uPhone);
        Double uMoney = tUser.getUMoney();
        return new JsonResult(200,"success",tUser,null);
    }

    /**
     * 查看用户订单
     */
    @GetMapping("/showUserOrder")
    public JsonResult showUserOrder(Integer uPhone){
        TUser tUser = tUserService.searchUserByPhone(uPhone);
        Integer uId = tUser.getUId();
        List<TOrder> tOrders = tOrderService.showUserOrder(uId);
        return new JsonResult(200,"success",null,tOrders);
    }

    /**
     * 用户发起订单
     */
    @PutMapping("/addUserOrder")
    public JsonResult addUserOrder(Integer coaId,Integer uId,String coaName,String uName,String reservationTime,int uPhone,int status){
        status=0;
        tOrderService.addUserOrder(coaId,uId,coaName,uName,reservationTime,uPhone,status);
        return new JsonResult(200,"success",null,null);
    }
    /**
     * 用户确认订单
     */
    @PutMapping("/userConfirmOrder")
    public JsonResult userConfirmOrder(Integer ordId){
        //根据id查出当前哪条订单记录
        TOrder tOrder = tOrderService.selectOrder(ordId);
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
    public JsonResult selectCoach(Integer coaId){
        TCoach tCoach = tCoachService.selectCoach(coaId);
        return new JsonResult(200,"success",tCoach,null);
    }

    /**
     * 用户点击图文后查看场馆详细信息
     */
    @GetMapping("/selectVenue")
    public JsonResult selectVenue(Integer venId){
        TVenues tVenues = tVenuesService.selectVenue(venId);
        return new JsonResult(200,"success",tVenues,null);
    }

}


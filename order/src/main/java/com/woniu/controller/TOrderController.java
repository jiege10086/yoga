package com.woniu.controller;


import com.github.pagehelper.PageInfo;
import com.woniu.domain.TOrder;
import com.woniu.dto.CoaDtoToken;
import com.woniu.exception.NumberNotFoundException;
import com.woniu.service.CoachTradingService;
import com.woniu.service.TOrderService;
import com.woniu.utils.JSONResult;
import com.woniu.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2020-11-13
 */
@RestController
@RequestMapping("/tOrder")
public class TOrderController {
    @Resource
    private CoachTradingService coachTradingService;

    @Autowired
    private TOrderService tOrderService;
    //查询教练的订单
    @RequestMapping("/coaorder")
    public JSONResult selectOrderByCoa(@RequestHeader("X-token") String token, int pageSize, int pageIndex, int status) throws Throwable {
        if(token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }
        CoaDtoToken coach = JwtUtils.parseToken(token, CoaDtoToken.class);
        PageInfo<TOrder> order = tOrderService.selectOrderByCoa(Integer.parseInt(coach.getCoaId()), pageSize, pageIndex, status);
        return new JSONResult("200","查询成功",null,order);
    }

    //教练修改订单状态
    @RequestMapping("/updateOrd")
    public JSONResult updateOrderByCoa(@RequestHeader("X-token") String token,int ordId,int status) throws Throwable {
        if(token==null){
            throw new NumberNotFoundException("权限不足,您还未登陆");
        }
        CoaDtoToken coach = JwtUtils.parseToken(token, CoaDtoToken.class);
        tOrderService.updateOrderByCoa(Integer.parseInt(coach.getCoaId()),ordId,status);
        return new JSONResult("200","操作成功",null,null);
    }

    //用户新增订单
    @RequestMapping("/insertOrderByUser")
    public JSONResult insertOrderByUser(int uid, String uName, int coaId, String phone) throws NumberNotFoundException {
        tOrderService.insertOrderByCoaId(uid,uName,coaId,phone);
        return new JSONResult("200","邀请成功",null,null);
    }

    //用户付款
    @RequestMapping("/updateOrderByMoney")
    public JSONResult updateOrderByMoney(int ordId) throws NumberNotFoundException{
        tOrderService.updateOrderByMoney(ordId);
        return new JSONResult("200","付款成功",null,null);
    }

    //用户查询自己的订单
    @RequestMapping("/selectOrderByUser")
    public JSONResult selectOrderByUser(int uId, int pageSize, int pageIndex, int status){
        return new JSONResult("200","付款成功",null,tOrderService.selectOrderByUser(uId,pageSize,pageIndex,status));
    }

    //用户对订单进行评价
    @RequestMapping("/updateCaotarding")
    public JSONResult updateCaotarding(int caoId, int status){
        coachTradingService.updateCaotarding(caoId,status);
        return new JSONResult("200","付款成功",null,null);
    }
}


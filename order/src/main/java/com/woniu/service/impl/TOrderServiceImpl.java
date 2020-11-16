package com.woniu.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.domain.CoachTrading;
import com.woniu.domain.TCoach;
import com.woniu.domain.TOrder;
import com.woniu.domain.TUser;
import com.woniu.exception.NumberNotFoundException;
import com.woniu.mapper.CoachTradingMapper;
import com.woniu.mapper.TCoachMapper;
import com.woniu.mapper.TOrderMapper;
import com.woniu.mapper.TUserMapper;
import com.woniu.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiejiang
 * @since 2020-11-13
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {
    @Resource
    private TOrderMapper tOrderMapper;

    @Resource
    private TCoachMapper tCoachMapper;

    @Resource
    private CoachTradingMapper coachTradingMapper;

    @Resource
    private TUserMapper tUserMapper;

    //教练查看自己的订单
    @Override
    public PageInfo<TOrder> selectOrderByCoa(int coaId, int pageSize, int pageIndex, int status) {
        PageHelper.startPage(pageIndex, 10);
        if (status==-1){
            QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("coa_id",coaId);
            List<TOrder> orders = tOrderMapper.selectList(queryWrapper);
            PageInfo<TOrder> pageinfo=new PageInfo<TOrder>(orders);
            return pageinfo;
        }
        QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status",status);
        queryWrapper.eq("coa_id",coaId);
        List<TOrder> orders = tOrderMapper.selectList(queryWrapper);
        PageInfo<TOrder> pageinfo=new PageInfo<TOrder>(orders);
        return pageinfo;
    }

    //教练订单状态改变
    @Override
    public void updateOrderByCoa(int coaId,int ordId, int status) {
        TOrder tOrder = new TOrder();
        tOrder.setOrdId(ordId);
        tOrder.setStatus(status);
        //修改为同意状态
        if(status==1){
            //将用户id放入教练的我的学员
            TOrder order = tOrderMapper.selectById(ordId);
            TCoach tCoach = tCoachMapper.selectById(coaId);
            List<Integer> users = JSON.parseArray(tCoach.getMyUser(),Integer.class);
            users.add(order.getuId());
            tCoach.setMyUser(JSON.toJSONString(users));
            tCoachMapper.updateById(tCoach);
        }
        //状态为开始上课加入上课时间和秒数方便计算
        if (status==3){
            tOrder.setStartTime(new SimpleDateFormat("yyyyMMddHHMMSS").format(new Date()));
            tOrder.setStartTimeMillisecond(System.currentTimeMillis());
        }
        //状态为下课加入下课时间和秒数
        if (status==4){
            tOrder.setEndTime(new SimpleDateFormat("yyyyMMddHHMMSS").format(new Date()));
            tOrder.setEndTimeMillisecond(System.currentTimeMillis());
            TOrder byId = tOrderMapper.selectById(ordId);
            TCoach coach = tCoachMapper.selectById(coaId);
            long time = byId.getStartTimeMillisecond()-System.currentTimeMillis();
            double money = time / 1000 / 60 / 60 * coach.getPrice();
            tOrder.setMoney(money);
        }
        tOrderMapper.updateById(tOrder);
    }

    //用户对教练发起订单请求
    @Override
    public void insertOrderByCoaId(int uid, String uName, int coaId, String phone) throws NumberNotFoundException {
        //判断用户余额够不够
        if(tUserMapper.selectById(uid).getuMoney()<tCoachMapper.selectById(coaId).getPrice()){
            throw new NumberNotFoundException("邀请失败,您的余额不足,请充值");
        }

        QueryWrapper<TOrder> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.eq("u_id",uid);
        QueryWrapper.eq("coa_id",coaId);
        QueryWrapper.eq("status",0);
        if(tOrderMapper.selectOne(QueryWrapper)!=null){
            tOrderMapper.delete(QueryWrapper);
        }
        TOrder tOrder = new TOrder();
        TCoach coach = tCoachMapper.selectById(coaId);
        tOrder.setCoaId(coaId);
        tOrder.setCoaName(coach.getCoaName());
        tOrder.setuId(uid);
        tOrder.setuName(uName);
        tOrder.setStatus(0);
        tOrderMapper.insert(tOrder);
    }

    //用户对订单进行付款
    @Override
    public void updateOrderByMoney(int ordId) throws NumberNotFoundException {
        TOrder tOrder = tOrderMapper.selectById(ordId);

        TUser tUser = tUserMapper.selectById(tOrder.getuId());

        if (tUser.getuMoney()<tOrder.getMoney()){
            throw new NumberNotFoundException("您的余额不足,请充值");
        }

        tOrder.setStatus(6);
        tOrderMapper.updateById(tOrder);
        //用户减钱,教练加钱
        tUser.setuMoney(tUser.getuMoney()-tOrder.getMoney());
        tUserMapper.updateById(tUser);
        TCoach tCoach = tCoachMapper.selectById(tOrder.getCoaId());
        tCoach.setCoaMoney(tCoach.getCoaMoney()+tOrder.getMoney());
        tCoachMapper.updateById(tCoach);

        //评价表看教练是否是第一次签约
        QueryWrapper<CoachTrading> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("coa_id",tOrder.getCoaId());
        CoachTrading coachTrading = coachTradingMapper.selectOne(queryWrapper);
        if (coachTrading==null){
            coachTrading= new CoachTrading();
            coachTrading.setClinchSuccess(1);
            coachTrading.setCoaId(tCoach.getCoaId());
            coachTradingMapper.insert(coachTrading);
        }
        //完成一次交易
        coachTrading.setClinchSuccess(coachTrading.getClinchSuccess()+1);
        coachTradingMapper.updateById(coachTrading);
    }

    //用户查看自己的订单
    @Override
    public PageInfo<TOrder> selectOrderByUser(int uId, int pageSize, int pageIndex, int status) {
        PageHelper.startPage(pageIndex, pageSize);
        if (status==-1){
            QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("u_id",uId);
            List<TOrder> orders = tOrderMapper.selectList(queryWrapper);
            PageInfo<TOrder> pageinfo=new PageInfo<TOrder>(orders);
            return pageinfo;
        }
        QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status",status);
        queryWrapper.eq("u_id",uId);
        List<TOrder> orders = tOrderMapper.selectList(queryWrapper);
        PageInfo<TOrder> pageinfo=new PageInfo<TOrder>(orders);
        return pageinfo;
    }


}

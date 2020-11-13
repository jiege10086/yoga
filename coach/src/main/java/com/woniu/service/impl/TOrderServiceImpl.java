package com.woniu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.domain.TCoach;
import com.woniu.domain.TOrder;
import com.woniu.mapper.TCoachMapper;
import com.woniu.mapper.TOrderMapper;
import com.woniu.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2020-11-10
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {
    @Resource
    private TOrderMapper tOrderMapper;

    @Resource
    private TCoachMapper tCoachMapper;

    //教练查看自己的订单
    @Override
    public PageInfo<TOrder> selectOrderByCoa(int coaId, int pageSize, int pageIndex,int status) {
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
}

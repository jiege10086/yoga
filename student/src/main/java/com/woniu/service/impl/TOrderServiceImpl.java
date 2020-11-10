package com.woniu.service.impl;

import com.woniu.dao.TOrderMapper;
import com.woniu.domain.TOrder;
import com.woniu.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lx
 * @since 2020-11-10
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {
    @Autowired
    private TOrderMapper tOrderMapper;

    @Override
    public List<TOrder> showUserOrder(Integer uId) {
        List<TOrder> tOrders = tOrderMapper.showUserOrder(uId);
        return tOrders;
    }

    @Override
    public void addUserOrder(Integer coaId, Integer uId, String coaName, String uName, String reservationTime, int uPhone, int status) {
        tOrderMapper.addUserOrder(coaId,uId,coaName,uName,reservationTime,uPhone,status);
    }

    @Override
    public TOrder selectOrder(Integer ordId) {
        return tOrderMapper.selectOrder(ordId);
    }

    @Override
    public void updateOrderStatus(Integer ordId) {
        tOrderMapper.updateOrderStatus(ordId);
    }
}

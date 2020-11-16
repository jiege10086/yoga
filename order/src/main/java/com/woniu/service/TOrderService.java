package com.woniu.service;

import com.github.pagehelper.PageInfo;
import com.woniu.domain.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.exception.NumberNotFoundException;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiejiang
 * @since 2020-11-13
 */
public interface TOrderService extends IService<TOrder> {
    //查询教练订单
    PageInfo<TOrder> selectOrderByCoa(int coaId, int pageSize, int pageIndex, int status);

    //教练接单
    void updateOrderByCoa(int coaId,int ordId,int status);

    //用户操作
    //用户发起对教练的申请
    void insertOrderByCoaId(int uid,String uName,int coaId,String phone) throws NumberNotFoundException;

    //用户对订单付款
    void updateOrderByMoney(int ordId) throws NumberNotFoundException;

    //用户查询订单
    PageInfo<TOrder> selectOrderByUser(int uId, int pageSize, int pageIndex, int status);
}

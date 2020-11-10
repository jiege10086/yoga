package com.woniu.service;

import com.github.pagehelper.PageInfo;
import com.woniu.domain.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiejiang
 * @since 2020-11-10
 */
public interface TOrderService extends IService<TOrder> {
    //查询教练订单
    PageInfo<TOrder> selectOrderByCoa(int coaId, int pageSize, int pageIndex, int status);

    //教练接单
    void updateOrderByCoa(int coaId,int ordId,int status);
}

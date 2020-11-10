package com.woniu.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.domain.TOrder;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lx
 * @since 2020-11-10
 */
public interface TOrderMapper extends BaseMapper<TOrder> {

    List<TOrder> showUserOrder(Integer uId);

    void addUserOrder(Integer coaId, Integer uId, String coaName, String uName, String reservationTime, int uPhone, int status);

    TOrder selectOrder(Integer ordId);

    void updateOrderStatus(Integer ordId);
}

package com.woniu.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.domain.TOrder;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lx
 * @since 2020-11-10
 */
public interface TOrderService extends IService<TOrder> {

    List<TOrder> showUserOrder(Integer uId);

    void addUserOrder(Integer coaId, Integer uId, String coaName, String uName, String reservationTime, int uPhone, int status);

    TOrder selectOrder(Integer ordId);

    void updateOrderStatus(Integer ordId);

    TOrder selectUserOrder(Integer uId, Integer coaId);

    void updateuserSubscribe(Integer uId, String uName, Integer coaId, String coaName, String reservationTime, int status, int uPhone);

    void adduserSubscribe(Integer uId, String uName, Integer coaId, String coaName, String reservationTime, int status, int uPhone);
}

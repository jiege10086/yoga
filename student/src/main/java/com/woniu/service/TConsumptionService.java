package com.woniu.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.domain.TConsumption;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lx
 * @since 2020-11-10
 */
public interface TConsumptionService extends IService<TConsumption> {

    void addUserMoney(Integer uId, double uMoney,Integer status,String detail,Integer peoRole);
}

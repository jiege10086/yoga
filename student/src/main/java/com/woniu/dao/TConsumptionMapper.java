package com.woniu.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.domain.TConsumption;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lx
 * @since 2020-11-10
 */
public interface TConsumptionMapper extends BaseMapper<TConsumption> {

    void addUserMoney(Integer uId, double uMoney,Integer status, String detail, Integer peoRole);
}

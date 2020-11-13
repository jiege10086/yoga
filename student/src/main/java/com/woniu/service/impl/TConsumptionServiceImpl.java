package com.woniu.service.impl;


import com.woniu.dao.TConsumptionMapper;
import com.woniu.domain.TConsumption;
import com.woniu.service.TConsumptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lx
 * @since 2020-11-10
 */
@Service
public class TConsumptionServiceImpl extends ServiceImpl<TConsumptionMapper, TConsumption> implements TConsumptionService {
    @Autowired
    private TConsumptionMapper tConsumptionMapper;
    @Override
    public void addUserMoney(Integer uId, double uMoney,Integer status,String detail,Integer peoRole) {
        tConsumptionMapper.addUserMoney(uId,uMoney,status,detail,peoRole);
    }
}

package com.woniu.service;

import com.woniu.domain.TCoach;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.param.CoaRegister;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiejiang
 * @since 2020-11-07
 */
public interface TCoachService extends IService<TCoach> {
    void insertCoach(CoaRegister coaRegister) throws Exception;

    void selectCoachById() throws Exception;

    TCoach CoachLogin(String name,String password) throws  Exception;

    void updateCoach(TCoach tCoach) throws Exception;

    String newPassword(String coaName) throws Exception;

    void newPasswordLogin(String coaName,String password) throws  Exception;

    void coaGetMoney(Integer bankcard,Double money,Integer coaId)throws  Exception;


}

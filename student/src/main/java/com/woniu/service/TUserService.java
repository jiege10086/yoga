package com.woniu.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.domain.TMessage;
import com.woniu.domain.TOrder;
import com.woniu.domain.TUser;
import com.woniu.dto.CoaAddressDtoList;
import com.woniu.exception.NumberNotFoundException;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lx
 * @since 2020-11-07
 */
public interface TUserService extends IService<TUser> {

    TUser searchUserByPhone(int uPhone);

    TUser searchUserByEmail(String uEmail);

    void registUserByPhone(int uPhone, String uPassword);

    void registUserByEmamil(String uEmail, String uPassword);

    TUser userLoginByPhone(int uPhone, String uPassword);

    TUser userLoginByEmail(String uEMail, String uPassword);

    void perfectUserInfoByUEmail(Integer uPhone, String uEMail, Integer uShowStatus, String uHeadPortrait, String uName, String uAddress, String uTruename, Integer uIdcard, Integer uQq);

    void perfectUserInfoByUphone(Integer uPhone, String uEMail, Integer uShowStatus, String uHeadPortrait, String uName, String uAddress, String uTruename, Integer uIdcard, Integer uQq);

    TUser searchUserById(Integer uId);

    void addUserMoney(Integer uId, double uMoney);

    void updateUserMoney(Integer uId, double money);

    void updateUserAttention(Integer userId, String json);

    void updateUserVenAttention(Integer userId, String json);

    void updateUserUserAttention(Integer userId, String json);

    List<CoaAddressDtoList> selectCoaByAddress(double longitude, double latitude, double fanwei);


    String newPassword(String name);

    void newPasswordLogin(String uName, String password) throws NumberNotFoundException;
}

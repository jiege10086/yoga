package com.woniu.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.domain.TUser;

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
}

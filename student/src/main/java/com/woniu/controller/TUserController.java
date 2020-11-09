package com.woniu.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.woniu.commons.JsonResult;
import com.woniu.domain.TUser;
import com.woniu.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Lx
 * @since 2020-11-07
 */
@RestController
@RequestMapping("/student")
public class TUserController {
    @Autowired
    private TUserService tUserService;

    /**
     * 用户手机注册
     * @param uPhone
     * @param uEMail
     * @return
     */
    @GetMapping("/searchUser")
    public JsonResult searchUser(Integer uPhone,String uEMail) {
        if (uPhone != null) {
            TUser tUser = tUserService.searchUserByPhone(uPhone);
            if (tUser != null) {
                return new JsonResult(200, "success", tUser);
            } else {
                return new JsonResult(400, "fail", null);
            }
        } else if (uEMail != null) {
            TUser tUser = tUserService.searchUserByEmail(uEMail);
            if (tUser != null) {
                return new JsonResult(200, "success", tUser);
            } else {
                return new JsonResult(400, "fail", null);
            }
        }
        return null;
    }

    /**
     * 用户邮箱注册
     * @param uPhone
     * @param uEMail
     * @param uPassword
     * @return
     */
    @PostMapping("/regist")
    public JsonResult registUser(Integer uPhone,String uEMail,String uPassword){
            if(uPhone!=null) {
                TUser tUser = tUserService.searchUserByPhone(uPhone);
                if(tUser==null){
                    tUserService.registUserByPhone(uPhone,uPassword);
                }
            }else if(uEMail != null){
                TUser tUser = tUserService.searchUserByEmail(uEMail);
                if(tUser==null) {
                    tUserService.registUserByEmamil(uEMail, uPassword);
                }
            }
               return new JsonResult(200,"success",null);
            }
    /**
     * 密码找回
     */
//    @GetMapping("/retrievepwd")
//    public JsonResult retrievePwd(int uPhone,String uEmail) {
//        if (uPhone != 0) {
//            TUser tUser = tUserService.retrievePwdByPhone(uPhone);
//            if (tUser != null) {
//                return new JsonResult(200, "success", tUser);
//            } else {
//                return new JsonResult(400, "fail", null);
//            }
//        } else if (uEmail != null) {
//            TUser tUser = tUserService.retrievePwdByEmail(uEmail);
//            if (tUser != null) {
//                return new JsonResult(200, "success", tUser);
//            } else {
//                return new JsonResult(400, "fail", null);
//            }
//        }
//    }
    /**
     * 用户的登录
     */
    @GetMapping("/userLogin")
    public JsonResult userLogin(Integer uPhone,String uEMail,String uPassword) {
        if (uPhone != null && uPassword != null) {
            TUser tUser = tUserService.userLoginByPhone(uPhone, uPassword);
            return new JsonResult(200,"success",tUser);
        } else {
            if (uEMail != null && uPassword != null) {
                TUser tUser = tUserService.userLoginByEmail(uEMail, uPassword);
                return new JsonResult(200,"success",tUser);
            } else {
                return new JsonResult(205, "输入手机或密码有误", null);
            }
        }

    }


    /**
     * 信息完善接口
     */
    @PutMapping("/perfectUserInfo")
    public JsonResult perfectUserInfo(Integer uPhone,String uEMail,Integer uShowStatus,String uHeadPortrait,String uName,String uAddress,String uTruename,Integer uIdcard,Integer uQq){
        TUser tUser = tUserService.searchUserByPhone(uPhone);
        if(tUser==null){
            tUserService.perfectUserInfoByUEmail(uPhone,uEMail,uShowStatus,uHeadPortrait,uName,uAddress,uTruename,uIdcard,uQq);
        }else {
            tUserService.perfectUserInfoByUphone(uPhone,uEMail,uShowStatus,uHeadPortrait,uName,uAddress,uTruename,uIdcard,uQq);
        }
            return new JsonResult(200,"success",null);
    }

}


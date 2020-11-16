package com.woniu.domain;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jiejiang
 * @since 2020-11-13
 */
public class TUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Integer uId;

    private Integer uPhone;

    private String uPassword;

    private Integer uQq;

    private String uEMail;

    private String uName;

    private String uHeadPortrait;

    private Integer uOpenid;

    private Integer uLevel;

    private Double uMoney;

    private Integer uShowStatus;

    private Integer uStatus;

    private String uAddress;

    private String uAttentionCoach;

    private Double uIntegral;

    private String uTruename;

    private Integer uIdcard;

    private String uAttentionUser;

    private String uAttentionVenues;


    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getuPhone() {
        return uPhone;
    }

    public void setuPhone(Integer uPhone) {
        this.uPhone = uPhone;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public Integer getuQq() {
        return uQq;
    }

    public void setuQq(Integer uQq) {
        this.uQq = uQq;
    }

    public String getuEMail() {
        return uEMail;
    }

    public void setuEMail(String uEMail) {
        this.uEMail = uEMail;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuHeadPortrait() {
        return uHeadPortrait;
    }

    public void setuHeadPortrait(String uHeadPortrait) {
        this.uHeadPortrait = uHeadPortrait;
    }

    public Integer getuOpenid() {
        return uOpenid;
    }

    public void setuOpenid(Integer uOpenid) {
        this.uOpenid = uOpenid;
    }

    public Integer getuLevel() {
        return uLevel;
    }

    public void setuLevel(Integer uLevel) {
        this.uLevel = uLevel;
    }

    public Double getuMoney() {
        return uMoney;
    }

    public void setuMoney(Double uMoney) {
        this.uMoney = uMoney;
    }

    public Integer getuShowStatus() {
        return uShowStatus;
    }

    public void setuShowStatus(Integer uShowStatus) {
        this.uShowStatus = uShowStatus;
    }

    public Integer getuStatus() {
        return uStatus;
    }

    public void setuStatus(Integer uStatus) {
        this.uStatus = uStatus;
    }

    public String getuAddress() {
        return uAddress;
    }

    public void setuAddress(String uAddress) {
        this.uAddress = uAddress;
    }

    public String getuAttentionCoach() {
        return uAttentionCoach;
    }

    public void setuAttentionCoach(String uAttentionCoach) {
        this.uAttentionCoach = uAttentionCoach;
    }

    public Double getuIntegral() {
        return uIntegral;
    }

    public void setuIntegral(Double uIntegral) {
        this.uIntegral = uIntegral;
    }

    public String getuTruename() {
        return uTruename;
    }

    public void setuTruename(String uTruename) {
        this.uTruename = uTruename;
    }

    public Integer getuIdcard() {
        return uIdcard;
    }

    public void setuIdcard(Integer uIdcard) {
        this.uIdcard = uIdcard;
    }

    public String getuAttentionUser() {
        return uAttentionUser;
    }

    public void setuAttentionUser(String uAttentionUser) {
        this.uAttentionUser = uAttentionUser;
    }

    public String getuAttentionVenues() {
        return uAttentionVenues;
    }

    public void setuAttentionVenues(String uAttentionVenues) {
        this.uAttentionVenues = uAttentionVenues;
    }

    @Override
    public String toString() {
        return "TUser{" +
        "uId=" + uId +
        ", uPhone=" + uPhone +
        ", uPassword=" + uPassword +
        ", uQq=" + uQq +
        ", uEMail=" + uEMail +
        ", uName=" + uName +
        ", uHeadPortrait=" + uHeadPortrait +
        ", uOpenid=" + uOpenid +
        ", uLevel=" + uLevel +
        ", uMoney=" + uMoney +
        ", uShowStatus=" + uShowStatus +
        ", uStatus=" + uStatus +
        ", uAddress=" + uAddress +
        ", uAttentionCoach=" + uAttentionCoach +
        ", uIntegral=" + uIntegral +
        ", uTruename=" + uTruename +
        ", uIdcard=" + uIdcard +
        ", uAttentionUser=" + uAttentionUser +
        ", uAttentionVenues=" + uAttentionVenues +
        "}";
    }
}

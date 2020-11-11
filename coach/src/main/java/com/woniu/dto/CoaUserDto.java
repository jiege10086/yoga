package com.woniu.dto;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

public class CoaUserDto implements Serializable {
    @TableId
    private Integer uId;

    private Integer uPhone;

    private String uName;

    private String uHeadPortrait;

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

    public Integer getuPhone() {
        return uPhone;
    }

    public void setuPhone(Integer uPhone) {
        this.uPhone = uPhone;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }
}

package com.woniu.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zly
 * @since 2020-11-09
 */
@Builder
public class TVenues implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ven_id", type = IdType.AUTO)
    private Integer venId;

    private Integer venPhone;

    private Integer openid;

    private String venAddress;

    private String venName;

    private Integer venQq;

    private String venImg;

    private String venDetail;

    private String venBulk;

    private String venCoachs;

    private String venPassword;

    private Integer venStatus;

    private String venEmail;


    public Integer getVenId() {
        return venId;
    }

    public void setVenId(Integer venId) {
        this.venId = venId;
    }

    public Integer getVenPhone() {
        return venPhone;
    }

    public void setVenPhone(Integer venPhone) {
        this.venPhone = venPhone;
    }

    public Integer getOpenid() {
        return openid;
    }

    public void setOpenid(Integer openid) {
        this.openid = openid;
    }

    public String getVenAddress() {
        return venAddress;
    }

    public void setVenAddress(String venAddress) {
        this.venAddress = venAddress;
    }

    public String getVenName() {
        return venName;
    }

    public void setVenName(String venName) {
        this.venName = venName;
    }

    public Integer getVenQq() {
        return venQq;
    }

    public void setVenQq(Integer venQq) {
        this.venQq = venQq;
    }

    public String getVenImg() {
        return venImg;
    }

    public void setVenImg(String venImg) {
        this.venImg = venImg;
    }

    public String getVenDetail() {
        return venDetail;
    }

    public void setVenDetail(String venDetail) {
        this.venDetail = venDetail;
    }

    public String getVenBulk() {
        return venBulk;
    }

    public void setVenBulk(String venBulk) {
        this.venBulk = venBulk;
    }

    public String getVenCoachs() {
        return venCoachs;
    }

    public void setVenCoachs(String venCoachs) {
        this.venCoachs = venCoachs;
    }

    public String getVenPassword() {
        return venPassword;
    }

    public void setVenPassword(String venPassword) {
        this.venPassword = venPassword;
    }

    public Integer getVenStatus() {
        return venStatus;
    }

    public void setVenStatus(Integer venStatus) {
        this.venStatus = venStatus;
    }

    public String getVenEmail() {
        return venEmail;
    }

    public void setVenEmail(String venEmail) {
        this.venEmail = venEmail;
    }

    @Override
    public String toString() {
        return "TVenues{" +
        "venId=" + venId +
        ", venPhone=" + venPhone +
        ", openid=" + openid +
        ", venAddress=" + venAddress +
        ", venName=" + venName +
        ", venQq=" + venQq +
        ", venImg=" + venImg +
        ", venDetail=" + venDetail +
        ", venBulk=" + venBulk +
        ", venCoachs=" + venCoachs +
        ", venPassword=" + venPassword +
        ", venStatus=" + venStatus +
        ", venEmail=" + venEmail +
        "}";
    }
}

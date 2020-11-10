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
 * @since 2020-11-07
 */
@Builder
public class TCourse implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "cou_id", type = IdType.AUTO)
    private Integer couId;

    private String couName;

    private String couTime;

    private String couDetail;

    private Integer coaId;

    private Integer venId;

    private String coaName;

    private String venName;

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }




    public Integer getCouId() {
        return couId;
    }

    public void setCouId(Integer couId) {
        this.couId = couId;
    }

    public String getCouName() {
        return couName;
    }

    public void setCouName(String couName) {
        this.couName = couName;
    }

    public String getCouTime() {
        return couTime;
    }

    public void setCouTime(String couTime) {
        this.couTime = couTime;
    }

    public String getCouDetail() {
        return couDetail;
    }

    public void setCouDetail(String couDetail) {
        this.couDetail = couDetail;
    }

    public Integer getCoaId() {
        return coaId;
    }

    public void setCoaId(Integer coaId) {
        this.coaId = coaId;
    }

    public Integer getVenId() {
        return venId;
    }

    public void setVenId(Integer venId) {
        this.venId = venId;
    }

    public String getCoaName() {
        return coaName;
    }

    public void setCoaName(String coaName) {
        this.coaName = coaName;
    }

    public String getVenName() {
        return venName;
    }

    public void setVenName(String venName) {
        this.venName = venName;
    }

    @Override
    public String toString() {
        return "TCourse{" +
        "couId=" + couId +
        ", couName=" + couName +
        ", couTime=" + couTime +
        ", couDetail=" + couDetail +
        ", coaId=" + coaId +
        ", venId=" + venId +
        ", coaName=" + coaName +
        ", venName=" + venName +
        "}";
    }
}

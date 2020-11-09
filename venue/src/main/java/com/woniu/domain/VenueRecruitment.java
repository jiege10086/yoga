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
public class VenueRecruitment implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "vrid", type = IdType.AUTO)
    private Integer vrid;

    private Integer venId;

    private Integer status;

    private String factions;

    private Double money;

    private String time;

    private String detail;


    public Integer getVrid() {
        return vrid;
    }

    public void setVrid(Integer vrid) {
        this.vrid = vrid;
    }

    public Integer getVenId() {
        return venId;
    }

    public void setVenId(Integer venId) {
        this.venId = venId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFactions() {
        return factions;
    }

    public void setFactions(String factions) {
        this.factions = factions;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "VenueRecruitment{" +
        "vrid=" + vrid +
        ", venId=" + venId +
        ", status=" + status +
        ", factions=" + factions +
        ", money=" + money +
        ", time=" + time +
        ", detail=" + detail +
        "}";
    }
}

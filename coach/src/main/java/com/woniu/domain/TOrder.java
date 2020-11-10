package com.woniu.domain;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jiejiang
 * @since 2020-11-10
 */
public class TOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Integer ordId;

    private Integer coaId;

    private String coaName;

    private Integer uId;

    private String uName;

    private String reservationTime;

    private String startTime;

    private String endTime;

    private Long startTimeMillisecond;

    private Long endTimeMillisecond;

    private Double money;

    private Integer status;

    private Integer uPhone;


    public Integer getOrdId() {
        return ordId;
    }

    public void setOrdId(Integer ordId) {
        this.ordId = ordId;
    }

    public Integer getCoaId() {
        return coaId;
    }

    public void setCoaId(Integer coaId) {
        this.coaId = coaId;
    }

    public String getCoaName() {
        return coaName;
    }

    public void setCoaName(String coaName) {
        this.coaName = coaName;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Long getStartTimeMillisecond() {
        return startTimeMillisecond;
    }

    public void setStartTimeMillisecond(Long startTimeMillisecond) {
        this.startTimeMillisecond = startTimeMillisecond;
    }

    public Long getEndTimeMillisecond() {
        return endTimeMillisecond;
    }

    public void setEndTimeMillisecond(Long endTimeMillisecond) {
        this.endTimeMillisecond = endTimeMillisecond;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getuPhone() {
        return uPhone;
    }

    public void setuPhone(Integer uPhone) {
        this.uPhone = uPhone;
    }

    @Override
    public String toString() {
        return "TOrder{" +
        "ordId=" + ordId +
        ", coaId=" + coaId +
        ", coaName=" + coaName +
        ", uId=" + uId +
        ", uName=" + uName +
        ", reservationTime=" + reservationTime +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", startTimeMillisecond=" + startTimeMillisecond +
        ", endTimeMillisecond=" + endTimeMillisecond +
        ", money=" + money +
        ", status=" + status +
        ", uPhone=" + uPhone +
        "}";
    }
}

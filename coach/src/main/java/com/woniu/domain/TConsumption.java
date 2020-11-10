package com.woniu.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jiejiang
 * @since 2020-11-09
 */
public class TConsumption implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "consu_id", type = IdType.AUTO)
    private Integer consuId;

    private Integer peopleId;

    private String time;

    private Double money;

    private Integer status;

    private String detail;

    private Integer peoRole;


    public Integer getConsuId() {
        return consuId;
    }

    public void setConsuId(Integer consuId) {
        this.consuId = consuId;
    }

    public Integer getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(Integer peopleId) {
        this.peopleId = peopleId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getPeoRole() {
        return peoRole;
    }

    public void setPeoRole(Integer peoRole) {
        this.peoRole = peoRole;
    }

    @Override
    public String toString() {
        return "TConsumption{" +
        "consuId=" + consuId +
        ", peopleId=" + peopleId +
        ", time=" + time +
        ", money=" + money +
        ", status=" + status +
        ", detail=" + detail +
        ", peoRole=" + peoRole +
        "}";
    }
}

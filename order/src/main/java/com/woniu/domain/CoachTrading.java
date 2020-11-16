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
 * @since 2020-11-14
 */
public class CoachTrading implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer coaId;

    private String coaName;

    private Integer clinchSuccess;

    private Integer clinchFalse;

    private Integer goodPraise;

    private Integer generalPraise;

    private Integer badPraise;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getClinchSuccess() {
        return clinchSuccess;
    }

    public void setClinchSuccess(Integer clinchSuccess) {
        this.clinchSuccess = clinchSuccess;
    }

    public Integer getClinchFalse() {
        return clinchFalse;
    }

    public void setClinchFalse(Integer clinchFalse) {
        this.clinchFalse = clinchFalse;
    }

    public Integer getGoodPraise() {
        return goodPraise;
    }

    public void setGoodPraise(Integer goodPraise) {
        this.goodPraise = goodPraise;
    }

    public Integer getGeneralPraise() {
        return generalPraise;
    }

    public void setGeneralPraise(Integer generalPraise) {
        this.generalPraise = generalPraise;
    }

    public Integer getBadPraise() {
        return badPraise;
    }

    public void setBadPraise(Integer badPraise) {
        this.badPraise = badPraise;
    }

    @Override
    public String toString() {
        return "CoachTrading{" +
        "id=" + id +
        ", coaId=" + coaId +
        ", coaName=" + coaName +
        ", clinchSuccess=" + clinchSuccess +
        ", clinchFalse=" + clinchFalse +
        ", goodPraise=" + goodPraise +
        ", generalPraise=" + generalPraise +
        ", badPraise=" + badPraise +
        "}";
    }
}

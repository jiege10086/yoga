package com.woniu.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zly
 * @since 2020-11-07
 */
public class VenueProducts implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "vpid", type = IdType.AUTO)
    private Integer vpid;

    private String card;

    private Double price;

    private Integer payNumber;

    private String detail;

    private String evaluation;

    private Integer venId;

    private Integer level;

    private String endTime;

    private String validTime;

    private String useRules;


    public Integer getVpid() {
        return vpid;
    }

    public void setVpid(Integer vpid) {
        this.vpid = vpid;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(Integer payNumber) {
        this.payNumber = payNumber;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public Integer getVenId() {
        return venId;
    }

    public void setVenId(Integer venId) {
        this.venId = venId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getValidTime() {
        return validTime;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }

    public String getUseRules() {
        return useRules;
    }

    public void setUseRules(String useRules) {
        this.useRules = useRules;
    }

    @Override
    public String toString() {
        return "VenueProducts{" +
        "vpid=" + vpid +
        ", card=" + card +
        ", price=" + price +
        ", payNumber=" + payNumber +
        ", detail=" + detail +
        ", evaluation=" + evaluation +
        ", venId=" + venId +
        ", level=" + level +
        ", endTime=" + endTime +
        ", validTime=" + validTime +
        ", useRules=" + useRules +
        "}";
    }
}

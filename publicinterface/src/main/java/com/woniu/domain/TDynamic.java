package com.woniu.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jiejiang
 * @since 2020-11-11
 */
public class TDynamic implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "dyn_id",type = IdType.AUTO)
    private Integer dynId;

    private LocalDateTime openTime;

    private String openDetail;

    private String imgs;

    private String peoName;

    private Integer uuid;

    private Integer peoRole;


    public Integer getDynId() {
        return dynId;
    }

    public void setDynId(Integer dynId) {
        this.dynId = dynId;
    }

    public LocalDateTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalDateTime openTime) {
        this.openTime = openTime;
    }

    public String getOpenDetail() {
        return openDetail;
    }

    public void setOpenDetail(String openDetail) {
        this.openDetail = openDetail;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public String getPeoName() {
        return peoName;
    }

    public void setPeoName(String peoName) {
        this.peoName = peoName;
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public Integer getPeoRole() {
        return peoRole;
    }

    public void setPeoRole(Integer peoRole) {
        this.peoRole = peoRole;
    }

    @Override
    public String toString() {
        return "TDynamic{" +
        "dynId=" + dynId +
        ", openTime=" + openTime +
        ", openDetail=" + openDetail +
        ", imgs=" + imgs +
        ", peoName=" + peoName +
        ", uuid=" + uuid +
        ", peoRole=" + peoRole +
        "}";
    }
}

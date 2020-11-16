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
 * @since 2020-11-16
 */
public class TSigning implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "sig_id", type = IdType.AUTO)
    private Integer sigId;

    private Integer sendId;

    private String detail;

    private Integer acceptId;

    private Integer agreeStatus;

    private Integer sendStatus;


    public Integer getSigId() {
        return sigId;
    }

    public void setSigId(Integer sigId) {
        this.sigId = sigId;
    }

    public Integer getSendId() {
        return sendId;
    }

    public void setSendId(Integer sendId) {
        this.sendId = sendId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getAcceptId() {
        return acceptId;
    }

    public void setAcceptId(Integer acceptId) {
        this.acceptId = acceptId;
    }

    public Integer getAgreeStatus() {
        return agreeStatus;
    }

    public void setAgreeStatus(Integer agreeStatus) {
        this.agreeStatus = agreeStatus;
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    @Override
    public String toString() {
        return "TSigning{" +
        "sigId=" + sigId +
        ", sendId=" + sendId +
        ", detail=" + detail +
        ", acceptId=" + acceptId +
        ", agreeStatus=" + agreeStatus +
        ", sendStatus=" + sendStatus +
        "}";
    }
}

package com.woniu.param;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

public class CoaMessageParam {

    private Integer sendId;

    private String detail;

    private String acceptId;

    private Integer peoRole;

    private String readStatus;

    private String sendName;

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

    public String getAcceptId() {
        return acceptId;
    }

    public void setAcceptId(String acceptId) {
        this.acceptId = acceptId;
    }

    public Integer getPeoRole() {
        return peoRole;
    }

    public void setPeoRole(Integer peoRole) {
        this.peoRole = peoRole;
    }

    public String getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    @Override
    public String toString() {
        return "TMessage{" +
                ", sendId=" + sendId +
                ", detail=" + detail +
                ", acceptId=" + acceptId +
                ", peoRole=" + peoRole +
                ", readStatus=" + readStatus +
                ", sendName=" + sendName +
                "}";
    }
}

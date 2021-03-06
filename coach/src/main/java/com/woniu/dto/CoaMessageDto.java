package com.woniu.dto;

import java.time.LocalDateTime;

public class CoaMessageDto {
    private Integer mesId;

    private Integer sendId;

    private String detail;

    private String acceptId;

    private Integer peoRole;

    private LocalDateTime mesTime;

    private String readStatus;

    private String sendName;


    public Integer getMesId() {
        return mesId;
    }

    public void setMesId(Integer mesId) {
        this.mesId = mesId;
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

    public LocalDateTime getMesTime() {
        return mesTime;
    }

    public void setMesTime(LocalDateTime mesTime) {
        this.mesTime = mesTime;
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
                "mesId=" + mesId +
                ", sendId=" + sendId +
                ", detail=" + detail +
                ", acceptId=" + acceptId +
                ", peoRole=" + peoRole +
                ", mesTime=" + mesTime +
                ", readStatus=" + readStatus +
                ", sendName=" + sendName +
                "}";
    }
}

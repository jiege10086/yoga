package com.woniu.param;

public class CoaMessageParam {


    private String detail;

    private String acceptId;

    private Integer peoRole;




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


    @Override
    public String toString() {
        return "TMessage{" +

                ", detail=" + detail +
                ", acceptId=" + acceptId +
                ", peoRole=" + peoRole +
                "}";
    }
}

package com.woniu.param;

public class CoaSelectParam extends ParamVO{
    private int timeStatus;
    private String coaName;

    public int getTimeStatus() {
        return timeStatus;
    }

    public void setTimeStatus(int timeStatus) {
        this.timeStatus = timeStatus;
    }

    public String getCoaName() {
        return coaName;
    }

    public void setCoaName(String coaName) {
        this.coaName = coaName;
    }
}

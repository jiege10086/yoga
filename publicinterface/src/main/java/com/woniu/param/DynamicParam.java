package com.woniu.param;

import java.util.List;

public class DynamicParam {
    private String openDetail;

    private List<String> imgs;

    private String peoName;

    private Integer uuid;

    private Integer peoRole;

    public String getOpenDetail() {
        return openDetail;
    }

    public void setOpenDetail(String openDetail) {
        this.openDetail = openDetail;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
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
        return "DynamicParam{" +
                "openDetail='" + openDetail + '\'' +
                ", imgs=" + imgs +
                ", peoName='" + peoName + '\'' +
                ", uuid=" + uuid +
                ", peoRole=" + peoRole +
                '}';
    }
}

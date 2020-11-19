package com.woniu.dto;

import lombok.Data;

@Data
public class TUserDtoToken {
    private String uId;

    private String role;

    private String uPhone;
    private String uEMail;
    private String uName;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public String getuEMail() {
        return uEMail;
    }

    public void setuEMail(String uEMail) {
        this.uEMail = uEMail;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }
}

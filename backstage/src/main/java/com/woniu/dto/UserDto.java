package com.woniu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer uId;

    private Integer uPhone;

    private String uPassword;

    private Integer uQq;

    private String uEMail;

    private String uName;

    private String uHeadPortrait;

    private Integer uOpenid;

    private Integer uLevel;

    private Double uMoney;

    private Integer uShowStatus;

    private Integer uStatus;

    private String uAddress;

    private String uAttentionCoach;

    private Double uIntegral;

    private String uTruename;

    private Integer uIdcard;

    private String uAttentionUser;

    private String uAttentionVenues;
}

package com.woniu.domain;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Lx
 * @since 2020-11-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TUser implements Serializable {

    private static final long serialVersionUID = 1L;

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

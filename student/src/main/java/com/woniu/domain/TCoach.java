package com.woniu.domain;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Lx
 * @since 2020-11-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TCoach implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer coaId;

    private Integer coaPhone;

    private Double price;

    private String factions;

    private Integer certificationStatus;

    private Integer morningStatus;

    private Integer afternoonStatus;

    private Integer nightStatus;

    private String coaName;

    private String coaPassword;

    private String coaHeadPortrait;

    private Double coaMoney;

    private Integer coaOpenid;

    private Integer coaStatus;

    private String coaAddress;

    private String attentionUser;

    private String attentionCoach;

    private String attentionVenues;

    private String coaEmail;

    private String belongsVenues;

    private Integer classFullStatus;

    private Integer privateStatus;

    private Integer showStatus;

    private Integer searchStatus;

    private Integer bankcard;

    private String truename;

    private Integer idcard;

    private String myUser;

    private Double expectMoney;


}

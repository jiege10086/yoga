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
public class TVenues implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer venId;

    private Integer venPhone;

    private Integer openid;

    private String venAddress;

    private String venName;

    private Integer venQq;

    private String venImg;

    private String venDetail;

    private String venBulk;

    private String venCoachs;

    private String venPassword;

    private Integer venStatus;


}

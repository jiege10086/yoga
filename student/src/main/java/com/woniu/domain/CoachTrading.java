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
 * @since 2020-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CoachTrading implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer id;

    private Integer coaId;

    private String coaName;

    private Integer clinchSuccess;

    private Integer clinchFalse;

    private Integer goodPraise;

    private Integer generalPraise;

    private Integer badPraise;


}

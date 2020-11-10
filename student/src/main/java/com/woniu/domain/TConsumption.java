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
 * @since 2020-11-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TConsumption implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer consuId;

    private Integer peopleId;

    private String time;

    private Double money;

    private Integer status;

    private String detail;

    private Integer peoRole;


}

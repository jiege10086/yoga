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
public class TOrder implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer ordId;

    private Integer coaId;

    private String coaName;

    private Integer uId;

    private String uName;

    private String reservationTime;

    private String startTime;

    private String endTime;

    private Long startTimeMillisecond;

    private Long endTimeMillisecond;

    private Double money;

    private Integer status;

    private Integer uPhone;


}

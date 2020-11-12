package com.woniu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
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

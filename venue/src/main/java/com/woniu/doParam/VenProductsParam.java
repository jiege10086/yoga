package com.woniu.doParam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenProductsParam {
    private Integer vpid;

    private String card;

    private Double price;

    private Integer payNumber;

    private String detail;

    private String evaluation;

    private Integer venId;

    private Integer level;

    private String endTime;

    private String validTime;

    private String useRules;

    private Integer pageSize;

    private Integer pageNum;
}

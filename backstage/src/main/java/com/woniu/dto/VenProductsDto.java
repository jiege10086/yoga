package com.woniu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenProductsDto {
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

}

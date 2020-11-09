package com.woniu.dot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenRecruitmentDot {
    private Integer vrid;

    private Integer venId;

    private Integer status;

    private String factions;

    private Double money;

    private String time;

    private String detail;


}

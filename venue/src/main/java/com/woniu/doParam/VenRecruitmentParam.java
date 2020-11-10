package com.woniu.doParam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenRecruitmentParam {
    private Integer vrid;

    private Integer venId;

    private Integer status;

    private String factions;

    private Double money;

    private String time;

    private String detail;

    private Integer pageSize;

    private Integer pageNum;
}

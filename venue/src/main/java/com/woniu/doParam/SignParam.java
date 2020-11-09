package com.woniu.doParam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignParam {
    private Integer sigId;

    private Integer sendId;

    private String detail;

    private Integer acceptId;

    private Integer agreeStatus;

    private Integer sendStatus;

    private Integer pageSize;

    private Integer pageNum;
}

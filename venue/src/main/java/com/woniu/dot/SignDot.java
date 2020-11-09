package com.woniu.dot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignDot {
    private Integer sigId;

    private Integer sendId;

    private String detail;

    private Integer acceptId;

    private Integer agreeStatus;

    private Integer sendStatus;

}

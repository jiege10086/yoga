package com.woniu.doParam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseParam {
    private Integer couId;

    private String couName;

    private String couTime;

    private String couDetail;

    private Integer coaId;

    private Integer venId;

    private String coaName;

    private String venName;

    private Integer pageSize;

    private Integer pageNum;

    private Integer status;
}

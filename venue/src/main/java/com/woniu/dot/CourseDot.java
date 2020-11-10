package com.woniu.dot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDot {
    private Integer couId;

    private String couName;

    private String couTime;

    private String couDetail;

    private Integer coaId;

    private Integer venId;

    private String coaName;

    private String venName;

    private Integer status;
}

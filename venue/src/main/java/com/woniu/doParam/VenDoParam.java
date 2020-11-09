package com.woniu.doParam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenDoParam {
    private Integer venId;

    private Integer venPhone;

    private Integer openid;

    private String venAddress;

    private String venName;

    private Integer venQq;

    private String venImg;

    private String venDetail;

    private String venBulk;

    private String venCoachs;

    private String venPassword;

    private Integer venStatus;

    private String venEmail;

    private Integer pageSize;

    private Integer pageNum;
}

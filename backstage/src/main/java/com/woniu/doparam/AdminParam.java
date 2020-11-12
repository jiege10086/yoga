package com.woniu.doparam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminParam {
    private Integer id;

    private String name;

    private Integer account;

    private String password;

    private Integer status;
}

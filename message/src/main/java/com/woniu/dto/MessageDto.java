package com.woniu.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDto {
    private Integer mesId;

    private Integer sendId;

    private String detail;

    private String acceptId;

    private Integer peoRole;

    private LocalDateTime mesTime;

    private String readStatus;

    private String sendName;
}

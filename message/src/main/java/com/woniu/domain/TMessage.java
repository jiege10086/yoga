package com.woniu.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Lx
 * @since 2020-11-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "mes_id", type = IdType.AUTO)
    private Integer mesId;

    private Integer sendId;

    private String detail;

    private String acceptId;

    private Integer peoRole;

    private LocalDateTime mesTime;

    private String readStatus;

    private String sendName;


}

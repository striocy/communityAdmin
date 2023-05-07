package com.cy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class AccessApproval {
    @TableId(type = IdType.AUTO,value = "`index`") private Integer index;
    private Integer requestIndex;
    private Timestamp approvalTime;
    private Integer adminId;
    private boolean isApproved;
    private Integer type;
    private String failedReason;
}

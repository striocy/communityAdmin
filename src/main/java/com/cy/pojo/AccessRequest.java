package com.cy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class AccessRequest {
    @TableId(type = IdType.AUTO,value = "`index`") private Integer index;
    private Timestamp time;
    private String name;
    private String address;
    private String destination;
    private Timestamp start;
    private Timestamp end;
    private String excuse;
    private String id;
    @TableField("`type`") private LeaveType type;
}

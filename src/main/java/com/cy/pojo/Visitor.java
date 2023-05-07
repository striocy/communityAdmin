package com.cy.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Visitor {
    @TableId(value = "`index`") private Integer index;
    private String name;
    private String id;
    private String tel;
    private String reason;
    private String destination;
    private String screenshot;
    private float temperature;
    private Timestamp submitTime;
    private Timestamp start;
    private Timestamp end;
}

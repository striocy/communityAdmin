package com.cy.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Access {
    @TableId(value = "`index`") private Integer index;
    private Integer nextDirection;
    private Integer leftTimes;
    private String id;
    private String name;
    private Timestamp start;
    private Timestamp deadline;
    private String administratorName;
    private Integer requestIndex;
}

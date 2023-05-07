package com.cy.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessHistory {
    @TableId(value = "`index`") private Integer index;
    private String id;
    private String name;
    private Timestamp accessTime;
    private String location;
    private boolean direction;
    private boolean resident;
}

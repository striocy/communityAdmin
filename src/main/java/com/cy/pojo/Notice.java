package com.cy.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
    @TableId("`index`") private Integer index;
    private Integer pulisherUid;
    private Timestamp publishTime;
    private String title;
    private Integer viewer;
    private Integer viewsCount;
    @TableField("`level`") private Integer level;
    private Integer contentIndex;
}

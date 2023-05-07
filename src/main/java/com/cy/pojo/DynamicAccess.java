package com.cy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DynamicAccess {
    @TableId(value = "`index`",type = IdType.AUTO) private Integer index;     //smallint(5)
    private Timestamp setTime;
    private Integer visit;
    private Integer oneDay;
    private Integer threeDays;
    @TableField("`enter`") private Integer enter;
    @TableField("`leave`") private Integer leave;
    public Integer getByName(String name){
        switch (name){
            case "visit" : return this.visit;
            case "oneDay" : return this.oneDay;
            case "threeDays" : return this.threeDays;
            case "enter" : return this.enter;
            case "leave" : return this.leave;
        }
        return -1;
    }
}

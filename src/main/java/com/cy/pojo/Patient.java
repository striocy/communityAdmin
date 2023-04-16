package com.cy.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@TableName("patient")
public class Patient {
    @TableId private String id;
    private String name;
    private Gender gender;
    @TableField("contact_time") private Timestamp time;
    private String contactPlace;
}

package com.cy.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@TableName("precaution")
public class Precaution {
    @MppMultiId private String id;
    @MppMultiId private Timestamp assessTime;
    private String name;
    private SafetyGrade grade;
    private String suggestion;
}

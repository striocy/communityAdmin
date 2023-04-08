package com.cy.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("health_info")
public class HealthInfo {

    @MppMultiId private String id;
    @MppMultiId  private Date submitTime;
    private String name;
    private int temprature;
    private int bloodPressure;
    private int breath;
    private String travelHistory;
    private String contactHistory;
}

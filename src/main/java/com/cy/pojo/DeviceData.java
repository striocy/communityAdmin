package com.cy.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@TableName("device_data")
public class DeviceData {
    @MppMultiId private Integer deviceId;    //smallint(5)
    private String  location;
    private DeviceType type;
    private String details;
    @MppMultiId private Timestamp dataTime;
}

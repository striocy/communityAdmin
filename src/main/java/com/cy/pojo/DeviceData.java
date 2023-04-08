package com.cy.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@TableName("device_data")
public class DeviceData {
    @TableId private int deviceId;
    private String  location;
    private DeviceType type;
    private String details;
    private Date dataTime;
}

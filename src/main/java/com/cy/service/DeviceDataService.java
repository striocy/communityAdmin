package com.cy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cy.implementer.DeviceDataImplementer;
import com.cy.pojo.DeviceData;
import com.cy.pojo.DeviceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class DeviceDataService {
    @Autowired private DeviceDataImplementer deviceDataImplementer;

    public List<DeviceData>listByLocation(String location){return this.deviceDataImplementer.selectLocation(location);

    }
    public List<DeviceData>listByType(DeviceType deviceType){return this.deviceDataImplementer.selectType(deviceType);}
    public List<DeviceData>listByTime(Date start,Date end){return this.deviceDataImplementer.selectTimeBetween(start,end);}
    public DeviceData getByDeviceId(Integer id){return this.deviceDataImplementer.deviceDM.selectById(id);}
    public boolean insert(DeviceData deviceData){
        if (this.deviceDataImplementer.deviceDM.insert(deviceData)==1) return true;
        return false;
    }
    public boolean delete(Integer id,Date date){if (this.deviceDataImplementer.delete(id,date)) return true;
    return false;
    }
    public boolean update(DeviceData deviceData){
        QueryWrapper wrapper=new QueryWrapper<>();
        wrapper.eq("device_id",deviceData.getDeviceId());
        wrapper.eq("data_time",deviceData.getDataTime());
        if (this.deviceDataImplementer.deviceDM.update(deviceData,wrapper)==1) return true;
        return false;
    }
    public List<DeviceData>listById(Integer id){
        QueryWrapper<DeviceData> wrapper=new QueryWrapper<>();
        wrapper.eq("device_id",id);
        return this.deviceDataImplementer.deviceDM.selectList(wrapper);
    }
}

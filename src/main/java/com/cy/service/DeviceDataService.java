package com.cy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.implementer.DeviceDataImplementer;
import com.cy.pojo.DeviceData;
import com.cy.pojo.DeviceType;
import com.cy.pojo.Precaution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.Timestamp;
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
    public boolean delete(Integer id, Timestamp date){if (this.deviceDataImplementer.delete(id,date)) return true;
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
    public List<DeviceData> select( String location,  int option,  DeviceType type,  Timestamp dataTime, String column,  int ascend,  int limit,  int page){
        QueryWrapper<DeviceData>wrapper=new QueryWrapper<>();
        Page<DeviceData> pages=new Page<>(page,limit);
        if (ascend==1)
            wrapper.orderByAsc(column);
        else wrapper.orderByDesc(column);
        wrapper.like("location",location);
        if (option!=1)
            wrapper.eq("type",type);
        wrapper.ge("data_time",dataTime);
        return this.deviceDataImplementer.deviceDM.selectList(wrapper);
    }
}

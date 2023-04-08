package com.cy.implementer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cy.mapper.DeviceDataMapper;
import com.cy.pojo.DeviceData;
import com.cy.pojo.DeviceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DeviceDataImplementer {
    @Autowired public DeviceDataMapper deviceDM;

    public List<DeviceData> selectLocation(String location){
        QueryWrapper<DeviceData> wrapper=new QueryWrapper<>();
        wrapper.like("location",location);
        return deviceDM.selectList(wrapper);
    }
    public List<DeviceData> selectType(DeviceType type){
        QueryWrapper<DeviceData> wrapper=new QueryWrapper<>();
        wrapper.eq("type",type);
        return deviceDM.selectList(wrapper);
    }
    public List<DeviceData> selectTimeBetween(Date start,Date end){
        QueryWrapper<DeviceData> wrapper=new QueryWrapper<>();
        wrapper.between("data_time",start,end);
        return deviceDM.selectList(wrapper);
    }
}

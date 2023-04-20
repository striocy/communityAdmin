package com.cy.controller;

import com.cy.myException.ValueInvalidException;
import com.cy.pojo.DeviceData;
import com.cy.pojo.DeviceType;
import com.cy.service.DeviceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    DeviceDataService deviceDataService;

    @GetMapping("listbyloc")
    public List<DeviceData> listByLocation(String location){
        return this.deviceDataService.listByLocation(location);
    }
    @GetMapping("listbytime")
    public List<DeviceData> listByTime(Date start,Date end)throws ValueInvalidException {
        if (start.after(end)) throw new ValueInvalidException("[start,end]","开始晚于结束时间");
        else return this.deviceDataService.listByTime(start, end);
    }
    @GetMapping("listbytype")
    public List<DeviceData> listByType(DeviceType type){
        return this.deviceDataService.listByType(type);
    }
    @PostMapping("deletedata")
    public int delete(Integer id,Date date){
        if (this.deviceDataService.delete(id,date))return 200;
        return 201;
    }
    @PostMapping("insertdata")
    public int insert(DeviceData deviceData)throws ValueInvalidException
    {
        if (deviceData==null)
            throw new ValueInvalidException("object","对象为空");
        if(this.deviceDataService.insert(deviceData)) return 200;
        return 201;
    }
    @PostMapping("updatedata")
    public int update(DeviceData deviceData){
        if (this.deviceDataService.update(deviceData)) return 200;
        return 201;
    }
    @GetMapping("listbyid")
    public List<DeviceData> listById(Integer id){
        return this.deviceDataService.listById(id);
    }
}

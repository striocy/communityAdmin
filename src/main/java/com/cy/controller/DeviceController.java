package com.cy.controller;

import com.cy.myException.ValueInvalidException;
import com.cy.pojo.DeviceData;
import com.cy.pojo.DeviceType;
import com.cy.service.DeviceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@CrossOrigin
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
    @PostMapping("delete")
    public int delete(Integer id, String dataTime) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        Timestamp date = new Timestamp(dateFormat.parse(dataTime).getTime());
        date.setHours(date.getHours()+8);
        if (this.deviceDataService.delete(id,date))return 200;
        return 201;
    }
    @PostMapping("insert")
    public int insert(@RequestBody DeviceData deviceData)throws ValueInvalidException
    {
        if (deviceData==null)
            throw new ValueInvalidException("object","对象为空");
        if(this.deviceDataService.insert(deviceData)) return 200;
        return 201;
    }
    @PostMapping("update")
    public int update(@RequestBody DeviceData deviceData){
        if (this.deviceDataService.update(deviceData)) return 200;
        return 201;
    }
    @GetMapping("listbyid")
    public List<DeviceData> listById(Integer id){
        return this.deviceDataService.listById(id);
    }
    @GetMapping("select")
    public List<DeviceData> select(@RequestParam String location, @RequestParam int option,@RequestParam DeviceType type, @RequestParam String dataTime,
                                   @RequestParam String column,@RequestParam int ascend,@RequestParam int limit,@RequestParam int page) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Timestamp date = new Timestamp(dateFormat.parse(dataTime).getTime());
        return this.deviceDataService.select(location,option,type,date,column,ascend,limit,page);
    }
}

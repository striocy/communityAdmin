package com.cy.controller;

import com.cy.myException.ValueInvalidException;
import com.cy.pojo.HealthInfo;
import com.cy.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/healthinfo")
public class HealthController {
    @Autowired private HealthService healthService;

    @GetMapping("listbyname")
    public List<HealthInfo> listByName(String name){return this.healthService.listByName(name);}
    @GetMapping("listbytemp")
    public List<HealthInfo> listByTemp(int temprature){return this.healthService.listByTemp(temprature);}
    @GetMapping("listbybldprs")
    public List<HealthInfo> listByBloodPressure(int pressure){return this.healthService.listByBloodPressure(pressure);}
    @GetMapping("listbybreath")
    public List<HealthInfo> listByBreath(int breath){return this.healthService.listByBreath(breath);}
    @GetMapping("listbytime")
    public List<HealthInfo> listByTime(Date start,Date end){return this.healthService.listByTime(start, end);}
    @PostMapping("insert")
    public int insert(HealthInfo healthInfo)throws ValueInvalidException {
        if (healthInfo==null) throw new ValueInvalidException("object","插入对象为空");
        if (this.healthService.insert(healthInfo)) return 200;
        return 201;
    }
    @PostMapping("delete")
    public int delete(String id,Date time){
        if(this.healthService.delete(id, time)) return 200;
        return 201;
    }
    @PostMapping("update")
    public int update(HealthInfo healthInfo) throws ValueInvalidException{
        if (healthInfo==null) throw new ValueInvalidException("object","对象为空");
        if (this.healthService.update(healthInfo)) return 200;
        return 201;
    }
}

package com.cy.controller;

import com.cy.myException.ValueInvalidException;
import com.cy.pojo.HealthInfo;
import com.cy.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin
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
    public int insert(@RequestBody HealthInfo healthInfo)throws ValueInvalidException {
        if (healthInfo==null) throw new ValueInvalidException("object","插入对象为空");
        System.out.println(healthInfo);
        if (this.healthService.insert(healthInfo)) return 200;
        return 201;
    }
    @PostMapping("delete")
    public int delete(@RequestParam String id,@RequestParam Date time){
        if(this.healthService.delete(id, time)) return 200;
        return 201;
    }
    @PostMapping("update")
    public int update(@RequestBody HealthInfo healthInfo) throws ValueInvalidException{
        System.out.println(healthInfo);
        if (healthInfo==null) throw new ValueInvalidException("object","对象为空");
        if (this.healthService.update(healthInfo)) return 200;
        return 201;
    }
    @GetMapping("select")
    public List<HealthInfo> select(@RequestParam int page,@RequestParam int limit, @RequestParam int ascend,
                                   @RequestParam String column,@RequestParam String name, @RequestParam int temprature,
                                   @RequestParam int bloodPressure,@RequestParam int breath,@RequestParam String travelHistory) throws ValueInvalidException {
        if (page<0||limit<0) throw new ValueInvalidException("value","参数不合法");
        return this.healthService.select(page, limit, ascend, column, name, temprature, bloodPressure, breath, travelHistory);

    }
}

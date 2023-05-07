package com.cy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.implementer.HealthInfoImplementer;
import com.cy.pojo.HealthInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class HealthService {
    @Autowired private HealthInfoImplementer healthInfoImplementer;

    public List<HealthInfo> listByName(String name){return this.healthInfoImplementer.selectNameLike(name);}
    public List<HealthInfo> listByTemp(int tempratrue){return this.healthInfoImplementer.selectTempGreatThan(tempratrue);}
    public List<HealthInfo> listByBloodPressure(int pressure){return this.healthInfoImplementer.selectBloodprsGreatThan(pressure);}
    public List<HealthInfo> listByBreath(int breath){return this.healthInfoImplementer.selectBreathGreatThan(breath);}
    public List<HealthInfo> listByTime(Date start , Date end){return this.healthInfoImplementer.selectTimeBetween(start, end);}
    public boolean insert(HealthInfo healthInfo){
        if(this.healthInfoImplementer.healthInfoMapper.insert(healthInfo)==1)return true;
        return false;
    }
    public boolean delete(String id,Date submitTime){
        QueryWrapper<HealthInfo> wrapper=new QueryWrapper<>();
        wrapper.eq("id",id);
        wrapper.eq("submit_time",submitTime);
        if (this.healthInfoImplementer.healthInfoMapper.delete(wrapper)==1) return true;
        return false;
    }
    public boolean update(HealthInfo healthInfo){
        QueryWrapper<HealthInfo> wrapper=new QueryWrapper<>();
        wrapper.eq("id",healthInfo.getId());
        wrapper.eq("submit_time",healthInfo.getSubmitTime());
        if (this.healthInfoImplementer.healthInfoMapper.update(healthInfo,wrapper)==1) return true;
        return false;
    }
    public List<HealthInfo> select(int page, int limit, int ascend, String column, String name, int temprature, int bloodPressure, int breath, String travelHistory){
        QueryWrapper<HealthInfo>wrapper=new QueryWrapper<>();
        Page<HealthInfo> pages=new Page<>(page,limit);
        if (ascend==1)
            wrapper.orderByAsc(column);
        else wrapper.orderByDesc(column);
        wrapper.like("name",name);
        wrapper.ge("temprature",temprature);
        wrapper.ge("blood_pressure",bloodPressure);
        wrapper.ge("breath",breath);
        wrapper.like("travel_history",travelHistory);
        return this.healthInfoImplementer.healthInfoMapper.selectList(wrapper);
    }
}

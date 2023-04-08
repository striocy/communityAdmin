package com.cy.implementer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cy.mapper.HealthInfoMapper;
import com.cy.pojo.HealthInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class HealthInfoImplementer {
    @Autowired  public HealthInfoMapper healthInfoMapper;
    public List<HealthInfo> selectNameLike(String name){
        QueryWrapper<HealthInfo> wrapper=new QueryWrapper<>();
        wrapper.like("name",name);
        return healthInfoMapper.selectList(wrapper);
    }
    public List<HealthInfo> selectTempGreatThan(int temprature){
        QueryWrapper<HealthInfo> wrapper=new QueryWrapper<>();
        wrapper.ge("temprature",temprature);
        return healthInfoMapper.selectList(wrapper);
    }
    public List<HealthInfo> selectBloodprsGreatThan(int pressure){
        QueryWrapper<HealthInfo> wrapper=new QueryWrapper<>();
        wrapper.ge("blood_pressure",pressure);
        return healthInfoMapper.selectList(wrapper);
    }
    public List<HealthInfo> selectBreathGreatThan(int breath){
        QueryWrapper<HealthInfo> wrapper=new QueryWrapper<>();
        wrapper.ge("breath",breath);
        return healthInfoMapper.selectList(wrapper);
    }
    public List<HealthInfo> selectTimeBetween(Date start,Date end){
        System.out.println(start+"\n"+end);
        QueryWrapper<HealthInfo> wrapper=new QueryWrapper<>();
        wrapper.between("submit_time",start,end);
        return healthInfoMapper.selectList(wrapper);
    }
}

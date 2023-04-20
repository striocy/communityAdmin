package com.cy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cy.implementer.PrecautionImplementer;
import com.cy.pojo.Precaution;
import com.cy.pojo.SafetyGrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PrecautionService {
    @Autowired public PrecautionImplementer precautionImplementer;
    public List<Precaution> listByTime(Timestamp start,Timestamp end){
        return this.precautionImplementer.selectAsseseTimeBetween(start, end);
    }
    public List<Precaution> listByGrade(SafetyGrade grade){
        return this.precautionImplementer.selectGrade(grade);
    }
    public int insert(Precaution precaution){
        return this.precautionImplementer.precautionMapper.insert(precaution);
    }
    public int update(Precaution precaution){
        QueryWrapper<Precaution> wrapper=new QueryWrapper<>();
        wrapper.eq("id",precaution.getId());
        wrapper.eq("assese_time",precaution.getAsseseTime());
        return this.precautionImplementer.precautionMapper.update(precaution,wrapper);
    }
    public int delete(String id,Timestamp time){
        QueryWrapper<Precaution> wrapper=new QueryWrapper<>();
        wrapper.eq("id",id);
        wrapper.eq("assese_time",time);
        return this.precautionImplementer.precautionMapper.delete(wrapper);
    }
}

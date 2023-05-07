package com.cy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.implementer.PrecautionImplementer;
import com.cy.pojo.HealthInfo;
import com.cy.pojo.Precaution;
import com.cy.pojo.SafetyGrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.Iterator;
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
        wrapper.eq("assess_time",precaution.getAssessTime());
        return this.precautionImplementer.precautionMapper.update(precaution,wrapper);
    }
    public int delete(String id,Timestamp time){
        QueryWrapper<Precaution> wrapper=new QueryWrapper<>();
        wrapper.eq("id",id);
        wrapper.eq("assess_time",time);
        return this.precautionImplementer.precautionMapper.delete(wrapper);
    }
    public List<Precaution> select(int option,String name, SafetyGrade grade, Timestamp assessTime, int page, int limit, int ascend, String column){
        QueryWrapper<Precaution>wrapper=new QueryWrapper<>();
        Page<Precaution> pages=new Page<>(page,limit);
        if (ascend==1)
            wrapper.orderByAsc(column);
        else wrapper.orderByDesc(column);
        wrapper.like("name",name);
        if (option!=1)
            wrapper.eq("grade",grade);
        wrapper.ge("assess_time",assessTime);
        return this.precautionImplementer.precautionMapper.selectList(wrapper);
    }
}

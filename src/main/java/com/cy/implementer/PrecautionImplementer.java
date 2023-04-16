package com.cy.implementer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cy.mapper.PrecautionMapper;
import com.cy.pojo.Precaution;
import com.cy.pojo.SafetyGrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PrecautionImplementer {
    @Autowired public PrecautionMapper precautionMapper;

    public List<Precaution> selectAsseseTimeBetween(Timestamp start,Timestamp end){
        QueryWrapper<Precaution> wrapper=new QueryWrapper<>();
        wrapper.between("assese_time",start,end);
        return precautionMapper.selectList(wrapper);
    }
    public List<Precaution> selectGrade(SafetyGrade grade){
        QueryWrapper<Precaution> wrapper=new QueryWrapper<>();
        wrapper.eq("grade",grade);
        return precautionMapper.selectList(wrapper);
    }
}

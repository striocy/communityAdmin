package com.cy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cy.mapper.QualifyMapper;
import com.cy.pojo.Qualify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QualifyService {
    @Autowired private QualifyMapper qualifyMapper;
    public int insert(Qualify qualify){
        return this.qualifyMapper.insert(qualify);
    }
    public int delete(Qualify qualify){
        return this.qualifyMapper.deleteById(qualify.getIndex());
    }
    public int update(Qualify qualify){
        return this.qualifyMapper.update(qualify,new QueryWrapper<Qualify>().eq("`index`",qualify.getIndex()));
    }
    public List<Qualify> findByReviewer(Integer uid){
        QueryWrapper<Qualify> wrapper=new QueryWrapper<>();
        wrapper.eq("reviewer_uid",uid);
        wrapper.eq("status",0);
        return this.qualifyMapper.selectList(wrapper);
    }
}

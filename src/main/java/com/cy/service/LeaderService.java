package com.cy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.mapper.LeaderMapper;
import com.cy.pojo.Leader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaderService {
    @Autowired private LeaderMapper leaderMapper;
    public Integer getLeader(Integer uid){
        Page<Leader> page=new Page<>(1,1);
        QueryWrapper<Leader> wrapper=new QueryWrapper<>();
        wrapper.eq("uid",uid);
        return this.leaderMapper.selectPage(page,wrapper).getRecords().get(0).getLeaderUid();
    }
}

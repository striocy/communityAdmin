package com.cy.implementer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cy.mapper.AccessApprovalMapper;
import com.cy.pojo.AccessApproval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessApprovalImplementer {
    @Autowired AccessApprovalMapper accessApprovalMapper;

    public int insert(AccessApproval accessApproval){
        return this.accessApprovalMapper.insert(accessApproval);
    }
    public int delete(Integer index){
        QueryWrapper<AccessApproval> wrapper=new QueryWrapper<>();
        wrapper.eq("index",index);
        return this.accessApprovalMapper.delete(wrapper);
    }
    public AccessApproval getByRequestId(Integer index){
        QueryWrapper<AccessApproval> wrapper=new QueryWrapper<>();
        wrapper.eq("request_index",index);
        return this.accessApprovalMapper.selectList(wrapper).get(0);
    }
}

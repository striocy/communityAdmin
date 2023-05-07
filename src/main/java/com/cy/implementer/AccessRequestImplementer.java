package com.cy.implementer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.mapper.AccessRequestMapper;
import com.cy.pojo.AccessApproval;
import com.cy.pojo.AccessRequest;
import com.cy.pojo.DynamicAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessRequestImplementer {
    @Autowired public AccessRequestMapper accessRequestMapper;

    public int delete(Integer index){
        QueryWrapper<AccessRequest> wrapper=new QueryWrapper<>();
        wrapper.eq("index",index);
        return this.accessRequestMapper.delete(wrapper);
    }
    public int insert(AccessRequest accessRequest){
        return this.accessRequestMapper.insert(accessRequest);
    }
    public int update(AccessRequest accessRequest){
        QueryWrapper<AccessRequest> wrapper=new QueryWrapper<>();
        wrapper.eq("index",accessRequest.getIndex());
        return this.accessRequestMapper.update(accessRequest,wrapper);
    }
    public AccessRequest getByIndex(Integer index){
        return this.accessRequestMapper.selectById(index);
    }
    public AccessRequest getByEntity(AccessRequest accessRequest){
        System.out.println(accessRequest);
        QueryWrapper<AccessRequest> wrapper=new QueryWrapper<>();
        wrapper.eq("time",accessRequest.getTime());
        wrapper.eq("name",accessRequest.getName());
        System.out.println(this.accessRequestMapper.selectList(wrapper));
        Page<AccessRequest> page=new Page<>(1,1);
        IPage<AccessRequest> test=this.accessRequestMapper.selectPage(page,wrapper);
        System.out.println(test.getRecords());
        return test.getRecords().get(0);
    }
}

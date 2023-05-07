package com.cy.implementer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cy.mapper.Access2BeApproveMapper;
import com.cy.pojo.Access2BeApprove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Access2BeApproveImplementer {
    @Autowired public Access2BeApproveMapper access2BeApproveMapper;
    public int insert(Access2BeApprove access2BeApprove){
        return this.access2BeApproveMapper.insert(access2BeApprove);
    }
    public int delete(Integer index){
        return this.access2BeApproveMapper.deleteById(index);
    }
    public List<Access2BeApprove> getList(Integer uid){
        return this.access2BeApproveMapper.selectList(new QueryWrapper<Access2BeApprove>().eq("reviewer_id",uid));
    }
    public Access2BeApprove getByRequestId(Integer index){
        QueryWrapper<Access2BeApprove> wrapper=new QueryWrapper<>();
        wrapper.eq("request_index",index);
        return this.access2BeApproveMapper.selectOne(wrapper);
    }
}

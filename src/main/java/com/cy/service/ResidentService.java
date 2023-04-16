package com.cy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.implementer.ResidentImplmenter;
import com.cy.pojo.Resident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResidentService {
    @Autowired private ResidentImplmenter residentImplmenter;

    public boolean createNewResident(Resident r){
        int temp=residentImplmenter.residentMapper.insert(r);
        if(temp==1)
            return true;
        return false;
    }
    public boolean deleteResident(String id){
        int temp=residentImplmenter.residentMapper.deleteById(id);
        if(temp==1)
            return true;
        return false;
    }
    public List<Resident> selectResidentList(int pageN,int count){
        Page<Resident> page=new Page<>(pageN,count);
        IPage<Resident> test=residentImplmenter.residentMapper.selectPage(page,null);
        return test.getRecords();
    }
}

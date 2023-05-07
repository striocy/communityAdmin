package com.cy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    public boolean update(Resident resident){
        QueryWrapper<Resident> wrapper=new QueryWrapper<>();
        wrapper.eq("id",resident.getId());
        int res=this.residentImplmenter.residentMapper.update(resident,wrapper);
        if(res==1) return true;
        return false;
    }
    public List<Resident> listByAgeGreaterThan(int age){
        return this.residentImplmenter.selectAgeGreaterThan(age);
    }
    public List<Resident> listByAgeLessThan(int age){
        return this.residentImplmenter.selectAgeLessThan(age);
    }
    public List<Resident> listByNameLike(String name){return this.residentImplmenter.selectNameLike(name);}
    public Resident getById(String id){return this.residentImplmenter.residentMapper.selectById(id);}
    public List<Resident> listByAddrLike(String addr){return this.residentImplmenter.selectAddressLike(addr);}
    public List<Resident> listSorted(int page,int n,int ascend,String column,String name,String address,Integer age){
        Page<Resident> pages=new Page<>(page,n);
        QueryWrapper<Resident> wrapper=new QueryWrapper<>();
        if (ascend==1)
            wrapper.orderByAsc(column);
        else wrapper.orderByDesc(column);
        wrapper.like("name",name);
        wrapper.like("address",address);
        wrapper.ge("age",age);
        IPage<Resident> test=residentImplmenter.residentMapper.selectPage(pages,wrapper);
        return test.getRecords();
    }
}

package com.cy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.implementer.PatientImplementer;
import com.cy.pojo.Gender;
import com.cy.pojo.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PatientService {
    @Autowired private PatientImplementer patientImplementer;

    public List<Patient> listByName(String name){return this.patientImplementer.selectNameLike(name);}
    public List<Patient> listByGender(Gender gender){return this.patientImplementer.selectGender(gender);}
    public List<Patient> listByTime(Timestamp start,Timestamp end){return this.patientImplementer.selectTimeBetween(start,end);}
    public List<Patient> listByPlace(String place){return this.patientImplementer.selectPlaceLike(place);}
    public boolean deleteById(String id){
        if(this.patientImplementer.patientMapper.deleteById(id)==1) return true;
        return false;
    }
    public boolean update(Patient patient){
        QueryWrapper<Patient> wrapper=new QueryWrapper<>();
        wrapper.eq("id",patient.getId());
        if (this.patientImplementer.patientMapper.update(patient,wrapper)==1) return true;
        return false;
    }
    public boolean insert(Patient patient){
        if (this.patientImplementer.patientMapper.insert(patient)==1)
            return true;
        return false;
    }
    public List<Patient> select(String name,String hospital,Timestamp time, int page,int n,int ascend,String column){
        Page<Patient> pages=new Page<>(page,n);
        QueryWrapper<Patient> wrapper=new QueryWrapper<>();
        if (ascend==1)
            wrapper.orderByAsc(column);
        else wrapper.orderByDesc(column);
        wrapper.like("name",name);
        wrapper.like("hospital",hospital);
        wrapper.ge("contact_time",time);
        IPage<Patient> test=this.patientImplementer.patientMapper.selectPage(pages,wrapper);
        return test.getRecords();
    }
}

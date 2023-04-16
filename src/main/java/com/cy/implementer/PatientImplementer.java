package com.cy.implementer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cy.mapper.PatientMapper;
import com.cy.pojo.Gender;
import com.cy.pojo.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PatientImplementer {
    @Autowired public PatientMapper patientMapper;

    public List<Patient> selectNameLike(String name){
        QueryWrapper<Patient> wrapper=new QueryWrapper<>();
        wrapper.like("name",name);
        return patientMapper.selectList(wrapper);
    }
    public List<Patient> selectGender(Gender gender){
        QueryWrapper<Patient> wrapper=new QueryWrapper<>();
        wrapper.eq("gender",gender);
        return patientMapper.selectList(wrapper);
    }
    public List<Patient> selectTimeBetween(Timestamp start,Timestamp end){
        QueryWrapper<Patient> wrapper=new QueryWrapper<>();
        wrapper.between("contact_time",start,end);
        return patientMapper.selectList(wrapper);
    }
    public List<Patient> selectPlaceLike(String place){
        QueryWrapper<Patient> wrapper=new QueryWrapper<>();
        wrapper.like("contact_place",place);
        return patientMapper.selectList(wrapper);
    }
}

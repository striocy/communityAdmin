package com.cy.controller;

import com.cy.myException.ValueInvalidException;
import com.cy.pojo.Patient;
import com.cy.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("patient")
public class PatientController {
    @Autowired private PatientService patientService;

    @GetMapping("listbyname")
    public List<Patient> listByName(String name){
        return this.patientService.listByName(name);
    }
    @GetMapping("listbytime")
    public List<Patient> listByTime(Timestamp start,Timestamp end)throws ValueInvalidException {
        if (start.after(end))
            throw new ValueInvalidException("start","开始时间晚于结束时间");
        return this.patientService.listByTime(start, end);
    }
    @GetMapping("listbyplace")
    public List<Patient>listByPlace(String place){ return this.patientService.listByPlace(place);}
    @PostMapping("insert")
    public int insert(Patient patient) throws ValueInvalidException{
        if (patient==null)
            throw new ValueInvalidException("object","对象为空");
        if (this.patientService.insert(patient)) return 200;
        return 201;
    }
    @PostMapping("delete")
    public int delete(String id){
        if (this.patientService.deleteById(id)) return 200;
        return 201;
    }
    @PostMapping("update")
    public int update(Patient patient){
        if (this.patientService.update(patient)) return 200;
        return 201;
    }
}

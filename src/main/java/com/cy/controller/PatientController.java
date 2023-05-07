package com.cy.controller;

import com.cy.myException.ValueInvalidException;
import com.cy.pojo.Patient;
import com.cy.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@CrossOrigin
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
    public int insert(@RequestBody Patient patient) throws ValueInvalidException{
        if (patient==null)
            throw new ValueInvalidException("object","对象为空");
        if (this.patientService.insert(patient)) return 200;
        return 201;
    }
    @PostMapping("delete")
    public int delete(@RequestParam String id){
        if (this.patientService.deleteById(id)) return 200;
        return 201;
    }
    @PostMapping("update")
    public int update(@RequestBody Patient patient){
        if (this.patientService.update(patient)) return 200;
        return 201;
    }
    @GetMapping("select")
    public List<Patient> select(@RequestParam String name,@RequestParam String hospital,@RequestParam String time,
                                @RequestParam int page,@RequestParam int limit,@RequestParam int ascend,@RequestParam String column) throws ValueInvalidException, ParseException {
        if (page<0||limit<0) throw new ValueInvalidException("page or n","参数非法");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Timestamp date = new Timestamp(dateFormat.parse(time).getTime());
        return this.patientService.select(name, hospital, date, page, limit, ascend, column);
    }
}

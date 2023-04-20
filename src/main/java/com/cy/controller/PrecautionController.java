package com.cy.controller;

import com.cy.myException.ValueInvalidException;
import com.cy.pojo.Precaution;
import com.cy.pojo.SafetyGrade;
import com.cy.service.PrecautionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/precaution")
public class PrecautionController {
    @Autowired private PrecautionService precautionService;

    @PostMapping("insert")
    public int insert(Precaution precaution)throws ValueInvalidException {
        if (precaution==null)
            throw new ValueInvalidException("object","插入对象为空");
        return 201-this.precautionService.insert(precaution);
    }
    @PostMapping("update")
    public int update(Precaution precaution){
        return 201-this.precautionService.update(precaution);
    }
    @PostMapping("delete")
    public int delete(String id, Timestamp time){
        return 201-this.precautionService.delete(id, time);
    }
    @GetMapping("listbytime")
    public List<Precaution> listByTime(Timestamp start,Timestamp end)throws ValueInvalidException{
        if (start.after(end)) throw new ValueInvalidException("[start,end]","开始时间晚于结束时间");
        return this.precautionService.listByTime(start, end);
    }
    @GetMapping("listbygrade")
    public List<Precaution> listByGrade(SafetyGrade grade){
        return this.precautionService.listByGrade(grade);
    }
}

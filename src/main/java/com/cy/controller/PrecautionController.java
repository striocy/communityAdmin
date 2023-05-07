package com.cy.controller;

import com.cy.myException.ValueInvalidException;
import com.cy.pojo.Precaution;
import com.cy.pojo.SafetyGrade;
import com.cy.service.PrecautionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/precaution")
public class PrecautionController {
    @Autowired private PrecautionService precautionService;

    @PostMapping("insert")
    public int insert(@RequestBody Precaution precaution)throws ValueInvalidException {
        if (precaution==null)
            throw new ValueInvalidException("object","插入对象为空");
        return 201-this.precautionService.insert(precaution);
    }
    @PostMapping("update")
    public int update(@RequestBody Precaution precaution){
        return 201-this.precautionService.update(precaution);
    }
    @PostMapping("delete")
    public int delete(String id,String time) throws ParseException {
        System.out.println(id+ ' '+ time);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        Timestamp date = new Timestamp(dateFormat.parse(time).getTime());
        date.setHours(date.getHours()+8);
        return 201-this.precautionService.delete(id, date);
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
    @GetMapping("select")
    public List<Precaution> select(@RequestParam int option,@RequestParam String name,@RequestParam SafetyGrade grade,@RequestParam String assessTime,
                                   @RequestParam int page, @RequestParam int limit,@RequestParam int ascend,@RequestParam String column) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Timestamp date = new Timestamp(dateFormat.parse(assessTime).getTime());
        return this.precautionService.select(option,name, grade, date, page, limit, ascend, column);
    }
}

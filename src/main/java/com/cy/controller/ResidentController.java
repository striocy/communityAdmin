package com.cy.controller;

import com.cy.myException.ValueInvalidException;
import com.cy.pojo.Resident;
import com.cy.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/resident")
public class ResidentController {
    @Autowired
    ResidentService residentService;

    @PostMapping("insert")
    public int residentInsert(@RequestBody Resident resident) throws ValueInvalidException{
        if (resident==null)
            throw new ValueInvalidException("object","对象为空");
        else {
        if(residentService.createNewResident(resident)) return 200;
        return 201;}
    }
    @GetMapping("getlist")
    public List<Resident> getResidents(int page,int n){
        return residentService.selectResidentList(page,n);
    }
    @PostMapping ("delete")
    public int deleteById(String id){
        if(residentService.deleteResident(id))
            return 200;
        return 201;
    }
    @PostMapping("update")
    public int update(@RequestBody Resident resident){
        if(resident==null){
            System.out.println("Resident object is null");
            return 404;}
        if (residentService.update(resident)) return 200;
        return 201;
    }
    @GetMapping("listbyage")
    public List<Resident> listByAge(int greater,int age) throws ValueInvalidException {
        System.out.println(greater);
        if(age<0||age>120)
            throw new ValueInvalidException("age","内容不合法");
        if (greater!=1&&greater!=0)
            throw new ValueInvalidException("greater","内容不合法");
        if (greater==1) return residentService.listByAgeGreaterThan(age);
        return residentService.listByAgeLessThan(age);
    }
    @GetMapping("listbyname")
    public List<Resident>listByName(String name){return this.residentService.listByNameLike(name);}
    @GetMapping("listbyaddr")
    public List<Resident>listByAddr(String addr){return this.residentService.listByAddrLike(addr);}
    @GetMapping("getbyid")
    public Resident getById(String id){return this.residentService.getById(id);
    }
    @GetMapping("listsorted")
    public List<Resident> listSorted(@RequestParam int page,@RequestParam int limit,@RequestParam int ascend,
                                     @RequestParam String column,@RequestParam String name,
                                     @RequestParam String address,@RequestParam Integer age) throws ValueInvalidException{
        if (page<0) throw new ValueInvalidException("page","page值非法");
        return this.residentService.listSorted(page, limit, ascend, column,name,address,age);
    }
}

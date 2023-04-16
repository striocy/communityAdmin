package com.cy.controller;

import com.cy.pojo.Resident;
import com.cy.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ResidentController {
    @Autowired
    ResidentService residentService;

    @PostMapping("/residentin")
    public int residentInsert(Resident resident){
        if(residentService.createNewResident(resident)) return 200;
        return 201;
    }
    @GetMapping("/residentget")
    public List<Resident> getResidents(int page,int n){
        return residentService.selectResidentList(page,n);
    }
}

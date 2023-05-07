package com.cy.controller;

import com.cy.pojo.Qualify;
import com.cy.service.LeaderService;
import com.cy.service.QualifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("visitor")
@CrossOrigin
public class VisitorController {
    @Autowired private QualifyService qualifyService;
    @Autowired private LeaderService leaderService;
    @PostMapping("becomeresident")
    public int becomeResident(@RequestBody Qualify qualify){
        qualify.setReviewerUid(this.leaderService.getLeader(qualify.getUid()));
        return this.qualifyService.insert(qualify);
    }
}

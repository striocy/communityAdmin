package com.cy.controller;

import com.cy.pojo.Resident;
import com.cy.pojo.User;
import com.cy.service.ResidentService;
import com.cy.service.UserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
class MyJSON{
    private int status;
    private Integer uid=0;
    private String identity;
    private String name;
    private String id;
    private Timestamp lastLogin;

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setIdentity(String identiry) {
        this.identity = identiry;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public int getStatus() {
        return status;
    }

    public Integer getUid() {
        return uid;
    }

    public String getIdentity() {
        return identity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }
}
@RestController
@CrossOrigin
public class UserController {
    @Autowired private UserService userService;
    @Autowired private ResidentService residentService;
    @PostMapping("/register")
    public int register(@RequestBody User user){
        user.setUid(0);
        user.setRegisterTime(new Timestamp(System.currentTimeMillis()));
        user.setIdentity(0);
        return 201-this.userService.userInsert(user);
    }
    @PostMapping("/signout")
    public int signOut(@RequestParam Integer uid){
        return 201-this.userService.userDelete(uid);
    }
    @PostMapping("/useredit")
    public int userUpdate(@RequestBody User user){
        return 201-this.userService.userUpdate(user);
    }
    @GetMapping ("/login")
    public MyJSON userLogin(@RequestParam String username, @RequestParam String password){
        User user=this.userService.selectByUserName(username);
        MyJSON result= new MyJSON();
        if (user.getPassword().equals(password)){
            result.setStatus(200);
            Resident resident=residentService.getById(user.getId());
            result.setId(user.getId());
            result.setName(resident.getName());
            result.setLastLogin(user.getLastLogin());
            result.setUid(user.getUid());
            switch (user.getIdentity()){
                case 0: result.setIdentity("visitor");break;
                case 1: result.setIdentity("resident");break;
                case 2: result.setIdentity("admin");break;
                case 3: result.setIdentity("system");
            }
            user.setLastLogin(new Timestamp(System.currentTimeMillis()));
            this.userService.userUpdate(user);
        }
        else result.setStatus(201);
        return result;
    }
    @GetMapping("userinfo")
    public User getUserInfo(@RequestParam Integer uid){
        return this.userService.findByUid(uid);
    }
}

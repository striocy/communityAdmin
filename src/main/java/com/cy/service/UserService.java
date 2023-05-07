package com.cy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cy.mapper.UserMapper;
import com.cy.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired private UserMapper userMapper;
    public int userInsert(User user){
        return this.userMapper.insert(user);
    }
    public int userDelete(Integer uid){
        return this.userMapper.deleteById(uid);
    }
    public int userUpdate(User user){
        return this.userMapper.update(user,new QueryWrapper<User>().eq("uid",user.getUid()));
    }
    public User selectByUserName(String userName){
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.like("user_name",userName);
        return this.userMapper.selectList(wrapper).get(0);
    }
    public User findByUid(Integer uid){
        return this.userMapper.selectById(uid);
    }
}

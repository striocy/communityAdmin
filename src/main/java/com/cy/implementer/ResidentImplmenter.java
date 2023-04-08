package com.cy.implementer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cy.mapper.ResidentMapper;
import com.cy.myException.EntityNotFoundException;
import com.cy.pojo.Gender;
import com.cy.pojo.Resident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;

@Service
public class ResidentImplmenter {
    @Autowired public ResidentMapper residentMapper;
    public ResidentImplmenter(){
        if (residentMapper==null){
            System.out.println("FUck\n");
        }
    }

    public List<Resident> selectAgeLessThan (int age){
        QueryWrapper<Resident> wrapper= new QueryWrapper<>();
        wrapper.le("age",age);
        return residentMapper.selectList(wrapper);
    }
    public List<Resident> selectNameLike(String name){
        QueryWrapper wrapper=new QueryWrapper<>();
        wrapper.like("name",name);
        return residentMapper.selectList(wrapper);
    }
    public List<Resident> selectAddressLike(String address){
        QueryWrapper<Resident> wrapper=new QueryWrapper<>();
        wrapper.like("address",address);
        return residentMapper.selectList(wrapper);
    }
    public List<Resident> selectAgeGreateThan(int age){
        QueryWrapper<Resident> wrapper=new QueryWrapper<>();
        wrapper.ge("age",age);
        return residentMapper.selectList(wrapper);
    }
    public int updateName(String id,String name){
        Resident resident=residentMapper.selectById(id);
        try{
            if (resident==null) throw new EntityNotFoundException("resident not found");
        }catch (EntityNotFoundException e){
            System.out.println("id:"+id+" "+e.msg);
        }
        resident.setName(name);
        return residentMapper.updateById(resident);
    }
    public int updateAddress(String id,String address){
        Resident resident=residentMapper.selectById(id);
        try{
            if (resident==null) throw new EntityNotFoundException("resident not found");
        }catch (EntityNotFoundException e){
            System.out.println("id:"+id+" "+e.msg);
        }
        resident.setAddress(address);
        return residentMapper.updateById(resident);
    }
    public int updateTel(String id,String tel){
        Resident resident=residentMapper.selectById(id);
        try{
            if (resident==null) throw new EntityNotFoundException("resident not found");
        }catch (EntityNotFoundException e){
            System.out.println("id:"+id+" "+e.msg);
        }
        resident.setTel(tel);
        return residentMapper.updateById(resident);
    }
    public int updateAge(String id,int age){
        Resident resident=residentMapper.selectById(id);
        try{
            if (resident==null) throw new EntityNotFoundException("resident not found");
        }catch (EntityNotFoundException e){
            System.out.println("id:"+id+" "+e.msg);
        }
        resident.setAge(age);
        return residentMapper.updateById(resident);
    }
    public int updateGender(String id, Gender gender){
        Resident resident=residentMapper.selectById(id);
        try{
            if (resident==null) throw new EntityNotFoundException("resident not found");
        }catch (EntityNotFoundException e){
            System.out.println("id:"+id+" "+e.msg);
        }
        resident.setGender(gender);
        return residentMapper.updateById(resident);
    }
}

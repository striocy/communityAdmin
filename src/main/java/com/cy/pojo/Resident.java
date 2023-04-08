package com.cy.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resident {
    private String name;
    private String address;
    private Gender gender;
    private  int age;
    private String tel;
    @TableId
    private String id;
}

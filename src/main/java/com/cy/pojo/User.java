package com.cy.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId private Integer uid;
    private Integer identity;
    private String userName;
    private String password;
    private Timestamp registerTime;
    private Timestamp lastLogin;
    private String id;
    private String tel;
}

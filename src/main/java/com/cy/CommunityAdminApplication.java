package com.cy;

import com.cy.mapper.ResidentMapper;
import com.github.jeffreyning.mybatisplus.conf.EnableMPP;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cy.mapper")
@EnableMPP
public class CommunityAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommunityAdminApplication.class, args);
    }

}

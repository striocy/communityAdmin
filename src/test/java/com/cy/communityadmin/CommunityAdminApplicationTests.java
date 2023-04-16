package com.cy.communityadmin;

import com.cy.implementer.HealthInfoImplementer;
import com.cy.implementer.PrecautionImplementer;
import com.cy.implementer.ResidentImplmenter;
import com.cy.mapper.ResidentMapper;
import com.cy.pojo.Gender;
import com.cy.pojo.HealthInfo;
import com.cy.pojo.Precaution;
import com.cy.pojo.Resident;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static com.cy.pojo.Gender.male;

@SpringBootTest
class CommunityAdminApplicationTests {
    //@Autowired private ResidentImplmenter res;
    //@Autowired private HealthInfoImplementer healthInfoImplementer;
    @Autowired private PrecautionImplementer precautionmapper;
    @Test
    void contextLoads() {
        //if(res.updateName("500231200101126236","Jobless")==0) System.out.println("Failed!");
        //List<HealthInfo>list=healthInfoImplementer.selectTimeBetween(new Date(2022-1900,1,1),new Date(2024-1900,5,1));
        Precaution list=precautionmapper.precautionMapper.selectById("500231200101126236");
        System.out.println(list.toString());
        //list.forEach(System.out::println);
    }

}

package com.cy.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Leader {
    @TableId(value = "`index`") private Integer index;
    private Integer uid;
    private Integer leaderUid;
    private String leaderName;
}

package com.cy.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Qualify {
    @TableId("`index`") private Integer index;
    private Integer uid;
    private String name;
    private String id;
    private String location;
    private Integer status;
    private Integer reviewerUid;
}

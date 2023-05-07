package com.cy.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Community {
    @TableId(value = "`index`")private Integer index;
    private String name;
    private String location;
    private String administratorId;
    private String administratorName;
    private String tel;
}

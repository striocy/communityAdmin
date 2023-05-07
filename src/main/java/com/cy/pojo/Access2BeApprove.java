package com.cy.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("access2be_approve")
public class Access2BeApprove {
    @TableId(type = IdType.AUTO,value = "`index`") private Integer index;
    private Integer requestIndex;
    private Integer type;
    private Integer reviewerUid;
}

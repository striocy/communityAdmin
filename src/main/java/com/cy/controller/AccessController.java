package com.cy.controller;

import com.cy.myException.ValueInvalidException;
import com.cy.pojo.Access2BeApprove;
import com.cy.pojo.AccessApproval;
import com.cy.pojo.AccessRequest;
import com.cy.pojo.DynamicAccess;
import com.cy.service.AccessService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/access")
public class AccessController {
    @Autowired AccessService accessService;
    @GetMapping("approvelist")
    public List<AccessRequest> approveList(Integer uid){
        return this.accessService.getList2BeApprove(uid);
    }
    @PostMapping("requestsubmit")
    public int requestSubmit(@RequestBody AccessRequest accessRequest,@RequestParam("uid") Integer uid) throws ValueInvalidException{
        if (accessRequest==null) throw new ValueInvalidException("object","插入对象为空");
        return 201-this.accessService.requestSubmit(accessRequest,uid);
    }
    @PostMapping("approval")
    public int accessApprove(@RequestBody AccessApproval accessApproval,@RequestParam("uid") Integer uid) throws ValueInvalidException{
        if (accessApproval==null) throw new ValueInvalidException("object","插入对象为空");
        return 201-this.accessService.accessApproval(accessApproval,uid);
    }
    @GetMapping("dynamicget")
    public DynamicAccess dynamicGet(){
        return this.accessService.DynamicAccessGet();
    }
    @PostMapping("dynamicdelete")
    public int dynamicDelete(Integer index){
        return 201-this.accessService.DynamicAccessDelete(index);
    }
    @PostMapping("dynamicinsert")
    public int dynamicInsert(@RequestBody DynamicAccess dynamicAccess){
        return 201-this.accessService.DynamicAccessInsert(dynamicAccess);
    }
    @GetMapping("gethistory")
    public List<SubmitHistory> getHistory(@RequestParam String id){
        List<SubmitHistory> list= new ArrayList<>();
        List<AccessRequest> request =this.accessService.getAccessRequestById(id);
        Iterator<AccessRequest> iterator =request.listIterator();
        while (iterator.hasNext()){
            AccessRequest temp=iterator.next();
            SubmitHistory history=new SubmitHistory();
            history.setRequest(temp);
            Access2BeApprove temp2;
            AccessApproval temp3;
            temp2=this.accessService.getAccess2BeApproveByIndex(temp.getIndex());
            if (temp2 == null){
                temp3 =this.accessService.getAccessApproveByIndex(temp.getIndex());
                if (temp3.isApproved()) history.setStatus("审核通过");
                else history.setStatus("审核不通过,原因:"+temp3.getFailedReason());
                list.add(history);
            }
            else {
                history.setStatus("审核中");
                list.add(history);
            }
        }
        return list;
    }
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class SubmitHistory{
    private AccessRequest request;
    private String status;
}

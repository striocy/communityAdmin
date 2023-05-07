package com.cy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.implementer.Access2BeApproveImplementer;
import com.cy.implementer.AccessApprovalImplementer;
import com.cy.implementer.AccessRequestImplementer;
import com.cy.mapper.*;
import com.cy.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class AccessService {
    @Autowired private AccessRequestImplementer aRI;
    @Autowired private AccessApprovalImplementer aAI;
    @Autowired private Access2BeApproveImplementer a2BAI;
    @Autowired private DynamicAccessMapper dAm;
    @Autowired private AccessMapper accessMapper;
    @Autowired private AccessHistoryMapper accessHistoryMapper;
    @Autowired private UserMapper userMapper;
    @Autowired private LeaderMapper leaderMapper;
    @Autowired private VisitorMapper visitorMapper;

    private int getDirection(LeaveType type){              //0为出,1为进
        int diretion=0;
        switch (type){
            case enter : diretion=1;break;
            case leave: diretion=0;break;
            case oneday:diretion=0;break;
            case threedays:diretion=0;break;
            default:
        }
        return diretion;
    }
    private int getLeftTimes(LeaveType type){
        int left=0;
        switch (type){
            case enter : left=1;break;
            case leave: left=2;break;
            case oneday:left=2;break;
            case threedays:left=2;break;
            default:
        }
        return left;
    }
    private Access getAccess(AccessRequest accessRequest,String adminName){
        int nextDirection=getDirection(accessRequest.getType());
        int leftTimes=getLeftTimes(accessRequest.getType());
        return new Access(0,nextDirection,leftTimes,accessRequest.getId(),
                accessRequest.getName(),accessRequest.getStart(),accessRequest.getEnd(),adminName,accessRequest.getIndex());
    }
    public int requestSubmit(AccessRequest accessRequest,Integer uid){
        int temp=0;
        System.out.println(accessRequest);
        Integer leaderUid=this.leaderMapper.selectList(new QueryWrapper<Leader>().eq("uid",uid)).get(0).getLeaderUid();
        if (this.aRI.insert(accessRequest)==1){
            System.out.println(accessRequest);
            Integer index=accessRequest.getIndex();
            temp=this.a2BAI.insert(new Access2BeApprove(0,index,0,leaderUid))==1? 1:0;
        }
        return temp;
    }
    public int accessApproval(AccessApproval accessApproval,Integer uid){
        if (accessApproval.isApproved()){
            DynamicAccess dynamicAccess=this.DynamicAccessGet();
            AccessRequest temp1=this.aRI.getByIndex(accessApproval.getRequestIndex());
            int reviewTimes=dynamicAccess.getByName(temp1.getType().getDataBaseName());
            Integer leaderUid=this.leaderMapper.selectList(new QueryWrapper<Leader>().eq("uid",uid)).get(0).getLeaderUid();
            if (leaderUid!=0 && reviewTimes>1)      //是否需要向上传递审批
                this.requestSubmit(temp1,uid);
            else {                                  //不需要,写入待出入
                this.accessMapper.insert(getAccess(temp1,leaderMapper.selectList(new QueryWrapper<Leader>().eq("uid",uid)).get(0).getLeaderName()));
            }
        }
        Access2BeApprove temp=this.a2BAI.getByRequestId(accessApproval.getRequestIndex());
        if (this.a2BAI.delete(temp.getIndex())==1){
            if (this.aAI.insert(accessApproval)==1)
                return 1;
            else {
                this.a2BAI.access2BeApproveMapper.insert(temp);
            }}
        return 0;
    }
    public List<AccessRequest> getList2BeApprove(Integer uid){
        List<Access2BeApprove> list=this.a2BAI.getList(uid);
        Iterator<Access2BeApprove> iterator = list.listIterator();
        List<AccessRequest> requests=new ArrayList<>();
        while (iterator.hasNext()){
            requests.add(this.aRI.getByIndex(iterator.next().getRequestIndex()));
        }
        return requests;
    }
    public int DynamicAccessInsert(DynamicAccess dynamicAccess){
        return this.dAm.insert(dynamicAccess);
    }
    public int DynamicAccessDelete(Integer index){
        return this.dAm.deleteById(index);
    }
    public DynamicAccess DynamicAccessGet(){
        Page<DynamicAccess> page=new Page<>(1,1);
        QueryWrapper<DynamicAccess> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("`index`");
        IPage<DynamicAccess> test=this.dAm.selectPage(page,wrapper);
        return test.getRecords().get(0);
    }
    public int accessUpdate(Access access){
        QueryWrapper<Access> wrapper=new QueryWrapper<>();
        wrapper.eq("`index`",access.getIndex());
        return this.accessMapper.update(access,wrapper);
    }
    public int accessHistoryInsert(AccessHistory accessHistory){
        return this.accessHistoryMapper.insert(accessHistory);
    }
    public int leaveExtention(Access access,Integer uid){
        AccessRequest temp=this.aRI.getByIndex(access.getRequestIndex());
        temp.setEnd(access.getDeadline());
        return this.requestSubmit(temp,uid);
    }
    public List<AccessRequest> getAccessRequestById(String id){
        return this.aRI.accessRequestMapper.selectList(new QueryWrapper<AccessRequest>().eq("id",id));
    }
    public Access2BeApprove getAccess2BeApproveByIndex(Integer index){
        return this.a2BAI.getByRequestId(index);
    }
    public AccessApproval getAccessApproveByIndex(Integer index){
        return this.aAI.getByRequestId(index);
    }
}

package com.cy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cy.mapper.NoticeContentMapper;
import com.cy.mapper.NoticeMapper;
import com.cy.pojo.Notice;
import com.cy.pojo.NoticeContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class NoticeService {
    @Autowired
    NoticeMapper noticeMapper;
    @Autowired
    NoticeContentMapper noticeContentMapper;

    public int noticeSubmit(Notice notice,String content){
        NoticeContent noticeContent=new NoticeContent();
        noticeContent.setContent(content);
        if(this.noticeContentMapper.insert(noticeContent)==1){
        notice.setContentIndex(noticeContent.getIndex());
        notice.setPublishTime(new Timestamp(System.currentTimeMillis()));
        if (this.noticeMapper.insert(notice)==1)
            return 1;
        }
        return 0;
    }
    public int noticeDelete(Integer noticeIndex,Integer contentIndex){
        if (this.noticeContentMapper.deleteById(contentIndex)==1){
        if (this.noticeMapper.deleteById(noticeIndex)==1)
            return 1;
    }
    return 0;}
    public List<Notice> noticeListGet(Integer leaderUid,Integer permission,String contentLike){
        QueryWrapper<Notice> wrapper=new QueryWrapper<>();
        wrapper.eq("publisher_uid",leaderUid);
        wrapper.le("viewer",permission);
        wrapper.like("title",contentLike);
        return this.noticeMapper.selectList(wrapper);
    }
    public String getContent(Integer index){
        return this.noticeContentMapper.selectById(index).getContent();
    }
    public void viewsCountAdd(Integer index){
        Notice temp=this.noticeMapper.selectById(index);
        temp.setViewsCount(temp.getViewsCount().intValue()+1);
    }
    public Notice getByIndex(Integer index){
        return this.noticeMapper.selectById(index);
    }
}

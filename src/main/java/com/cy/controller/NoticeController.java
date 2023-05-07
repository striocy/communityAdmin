package com.cy.controller;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cy.pojo.Notice;
import com.cy.service.NoticeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("notice")
@CrossOrigin
public class NoticeController {
    @Autowired
    NoticeService noticeService;
    @PostMapping("post")
    public int noticeSubmit(@RequestBody MyNotice myNotice){
        Notice notice=new Notice(0, myNotice.getPulisherUid(), null, myNotice.getTitle(), myNotice.getViewer(),
                myNotice.getViewsCount(), myNotice.getLevel(),null);
        return 201-this.noticeService.noticeSubmit(notice,myNotice.getContent());
    }
    @PostMapping("delete")
    public int noticeDelete(@RequestParam Integer noticeIndex,@RequestParam Integer contentIndex){
        return 201-this.noticeService.noticeDelete(noticeIndex, contentIndex);
    }
    @GetMapping("listget")
    public List<Notice> listGet(@RequestParam Integer publisherUid,@RequestParam Integer permission,@RequestParam String contentLike){
        return this.noticeService.noticeListGet(publisherUid, permission, contentLike);
    }
    @GetMapping("details")
    public MyNotice details(@RequestParam Integer index){
        Notice temp=this.noticeService.getByIndex(index);
        String content=this.noticeService.getContent(temp.getContentIndex());
        return new MyNotice(temp,content);
    }
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class MyNotice{
    private Integer index;
    private Integer pulisherUid;
    private Timestamp publishTime;
    private String title;
    private Integer viewer;
    private Integer viewsCount;
    private Integer level;
    private String content;
    public MyNotice(Notice notice,String content){
        this.index= notice.getIndex();
        this.pulisherUid= notice.getPulisherUid();
        this.title= notice.getTitle();
        this.level= notice.getLevel();
        this.viewer= notice.getViewer();
        this.content=content;
        this.viewsCount= notice.getViewsCount();
        this.publishTime=notice.getPublishTime();
    }
}

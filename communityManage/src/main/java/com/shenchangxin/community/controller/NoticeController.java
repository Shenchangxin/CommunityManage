package com.shenchangxin.community.controller;

import com.shenchangxin.community.entity.Result;
import com.shenchangxin.community.entity.StatusCode;
import com.shenchangxin.community.pojo.Notice;
import com.shenchangxin.community.service.NoticeService;
import com.shenchangxin.community.utils.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/api/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private FormatUtil formatUtil;

    @GetMapping("/getAllNotice")
    public Result getNotice(){
        try{
            List<Notice> noticeList = noticeService.getAllNotice();
            return Result.create(StatusCode.OK,"获取数据成功",noticeList);
        }catch (RuntimeException e){
            return Result.create(StatusCode.ERROR,"获取数据失败"+e.getMessage());
        }
    }

    @PostMapping("/addNotice")
    public Result addNotice(@RequestBody Notice notice){
        if (!formatUtil.checkObjectNull(notice)){
            return Result.create(StatusCode.ERROR,"参数值为空，请检查");
        }
        try{
            noticeService.addNotice(notice);
            return Result.create(StatusCode.OK,"插入数据成功");
        }catch (RuntimeException e){
            return Result.create(StatusCode.ERROR,"插入数据出错"+e.getMessage());
        }
    }

    @PostMapping("/updateNotice")
    public Result updateNotice(@RequestBody Notice notice){
        if (!formatUtil.checkObjectNull(notice)){
            return Result.create(StatusCode.ERROR,"参数值为空，请检查");
        }
        try{
            noticeService.updateNotice(notice);
            return Result.create(StatusCode.OK,"更新数据成功");
        }catch (RuntimeException e){
            return Result.create(StatusCode.ERROR,"更新数据出错"+e.getMessage());
        }
    }

    @DeleteMapping("/deleteNotice")
    public Result deleteNotice(@RequestParam("id")Integer id){
        if (!formatUtil.checkPositive(id)){
            return Result.create(StatusCode.ERROR,"参数值不能为空，请检查");
        }
        try{
            noticeService.deleteNoticeById(id);
            return Result.create(StatusCode.OK,"删除数据失败");
        }catch (RuntimeException e){
            return Result.create(StatusCode.ERROR,"插入数据出错"+e.getMessage());
        }
    }

    @GetMapping("/getNotice")
    public Result getNoticeById(@RequestParam("id") Integer id){
        if (!formatUtil.checkPositive(id)){
            return Result.create(StatusCode.ERROR,"参数值不能为空，请检查");
        }
        try{
            Notice notice = noticeService.findNoticeNyId(id);
            return Result.create(StatusCode.OK,"查询数据成功",notice);
        }catch (RuntimeException e){
            return Result.create(StatusCode.ERROR,"查询数据失败"+e.getMessage());
        }
    }


}

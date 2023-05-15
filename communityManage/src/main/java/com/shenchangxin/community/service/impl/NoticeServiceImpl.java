package com.shenchangxin.community.service.impl;


import com.shenchangxin.community.mapper.NoticeMapper;
import com.shenchangxin.community.pojo.Notice;
import com.shenchangxin.community.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;


    @Override
    public void addNotice(Notice notice) {

        noticeMapper.addNotice(notice);
    }

    @Override
    public void deleteNoticeById(Integer id) {

        noticeMapper.deleteNoticeById(id);
    }

    @Override
    public void updateNotice(Notice notice) {

        noticeMapper.updateNotice(notice);
    }

    @Override
    public Notice findNoticeNyId(Integer id) {
        return noticeMapper.findNoticeById(id);
    }

    @Override
    public List<Notice> getAllNotice() {
        List<Notice> notices = noticeMapper.getAllNotice();
        return notices;
    }
}

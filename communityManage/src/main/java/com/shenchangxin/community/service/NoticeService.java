package com.shenchangxin.community.service;


import com.shenchangxin.community.pojo.Notice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoticeService {

    void addNotice(Notice notice);

    void deleteNoticeById(Integer id);

    void updateNotice(Notice notice);

    Notice findNoticeNyId(Integer id);

    List<Notice> getAllNotice();
}

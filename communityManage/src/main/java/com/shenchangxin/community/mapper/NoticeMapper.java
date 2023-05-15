package com.shenchangxin.community.mapper;


import com.shenchangxin.community.pojo.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {

    void addNotice(Notice notice);

    void deleteNoticeById(Integer id);

    void updateNotice(Notice notice);

    Notice findNoticeById(Integer id);

    List<Notice> getAllNotice();
}

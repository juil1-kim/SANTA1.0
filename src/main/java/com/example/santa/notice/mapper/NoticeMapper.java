package com.example.santa.notice.mapper;

import com.example.santa.notice.vo.NoticeDTO;
import com.example.santa.notice.vo.NoticeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    List<NoticeDTO> selectAllNotice();
    NoticeDTO selectByNoticeId(Integer id);
    int insertNotice(NoticeVO notice);
    int deleteNotice(NoticeDTO notice);
}

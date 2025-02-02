package com.example.santa.notice.controller;

import com.example.santa.notice.service.NoticeService;
import com.example.santa.notice.vo.NoticeDTO;
import com.example.santa.notice.vo.NoticeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("notice")
    public String board(Model model) {
        System.out.println("========== notice 접속=======");
        List<NoticeDTO> noticeList = noticeService.selectAllNotice();
        System.out.println("======= noticeList ======" + noticeList);

        model.addAttribute("noticeList", noticeList);
        return "notice/notice";
    }

    @GetMapping("noticeWrite")
    public String noticeWriter() {
        return "notice/noticeWrite";
    }

    @PostMapping("noticeCreate")
    public String noticeCreate(NoticeVO noticeVO) {
        System.out.println("=========작성된 글 저장 요청 ========");
        System.out.println("NoticeVO = " + noticeVO);

        int result = noticeService.insertNotice(noticeVO);
        if(result > 0) {
            return "redirect:/notice/notice";
        } else {
            return "error/error";
        }
    }

    @GetMapping("read")
    public String read(int no, Model model) {
        System.out.println("========no=========" + no);
        NoticeDTO noticeDTO = noticeService.selectByNoticeId(no);
        System.out.println(noticeDTO);
        model.addAttribute("noticeDTO", noticeDTO);

        return "notice/noticeRead";
    }

    @DeleteMapping("delete/{id}")
    @ResponseBody
    public int delete(@PathVariable int id) {
        System.out.println("=====PathVariable =====" + id);
        return noticeService.deleteNotice(id);
    }


    @GetMapping("update")
    public String update(int no, Model model) {
        NoticeVO noticeVO = noticeService.selectByNoticeId2(no);
        System.out.println("updateNotice========"+noticeVO);

        model.addAttribute("noticeVO", noticeVO);
        return "notice/noticeUpdate";
    }

    @PostMapping("noticeUpdate")
    public String noticeUpdate(NoticeVO noticeVO) {
        System.out.println("=========작성된 글 저장 요청 ========");
        System.out.println("NoticeVO = " + noticeVO);

        int result = noticeService.updateNotice(noticeVO);
        if(result > 0) {
            return "redirect:/notice/notice";
        } else {
            return "error/error";
        }
    }
}

package com.dsm.nms.domain.notice.api;

import com.dsm.nms.domain.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class NoticeController {

    private final NoticeService noticeService;

}

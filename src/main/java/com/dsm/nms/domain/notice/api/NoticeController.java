package com.dsm.nms.domain.notice.api;

import com.dsm.nms.domain.notice.api.dto.request.ModifyNoticeRequest;
import com.dsm.nms.domain.notice.api.dto.request.RegisterNoticeRequest;
import com.dsm.nms.domain.notice.api.dto.response.NoticeResponse;
import com.dsm.nms.domain.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerNotice(@RequestPart @Valid RegisterNoticeRequest noticeRequest,
                               @RequestPart(required = false) List<MultipartFile> images) {
        noticeService.registerNotice(noticeRequest, images);
    }

    @PatchMapping("/{notice-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modifyNotice(@PathVariable("notice-id") Integer noticeId,
                             @RequestPart @Valid ModifyNoticeRequest noticeRequest,
                             @RequestPart(required = false) List<MultipartFile> images) {
        noticeService.modifyNotice(noticeId, noticeRequest, images);
    }

    @DeleteMapping("/{notice-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeNotice(@PathVariable("notice-id") Integer noticeId) {
        noticeService.removeNotice(noticeId);
    }

    @GetMapping
    public NoticeResponse getAllNotices() {
        return noticeService.getAllNotices();
    }

}

package com.dsm.nms.domain.notice.service;

import com.dsm.nms.domain.teacher.api.dto.request.RegisterNoticeRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NoticeService {
    void registerNotice(RegisterNoticeRequest noticeRequest, List<MultipartFile> images);
}

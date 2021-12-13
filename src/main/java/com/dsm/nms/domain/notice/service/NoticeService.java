package com.dsm.nms.domain.notice.service;

import com.dsm.nms.domain.notice.api.dto.request.ModifyNoticeRequest;
import com.dsm.nms.domain.notice.api.dto.request.RegisterNoticeRequest;
import com.dsm.nms.domain.notice.api.dto.response.SchoolResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NoticeService {
    void registerNotice(RegisterNoticeRequest noticeRequest, List<MultipartFile> images);
    void modifyNotice(Integer noticeId, ModifyNoticeRequest noticeRequest, List<MultipartFile> images);
    void removeNotice(Integer noticeId);
    SchoolResponse getAllNotices();
    SchoolResponse.notice getNotice(Integer noticeId);
    ResponseEntity<?> getNoticeToTarget(String target);
}

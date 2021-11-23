package com.dsm.nms.domain.notice.service;

import com.dsm.nms.domain.notice.api.dto.ModifyNoticeRequest;
import com.dsm.nms.domain.notice.api.dto.RegisterNoticeRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface NoticeService {
    void registerNotice(RegisterNoticeRequest noticeRequest, List<MultipartFile> images);
    void modifyNotice(Integer noticeId, ModifyNoticeRequest noticeRequest, List<Map<Integer, MultipartFile>> images);
}

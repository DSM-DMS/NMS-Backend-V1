package com.dsm.nms.domain.notice.service;

import com.dsm.nms.domain.image.facade.ImageFacade;
import com.dsm.nms.domain.notice.api.dto.ModifyNoticeRequest;
import com.dsm.nms.domain.notice.entity.Notice;
import com.dsm.nms.domain.notice.exception.NoticeNotFoundException;
import com.dsm.nms.domain.notice.facade.NoticeFacade;
import com.dsm.nms.domain.notice.repository.NoticeRepository;
import com.dsm.nms.domain.notice.api.dto.RegisterNoticeRequest;
import com.dsm.nms.domain.teacher.entity.Teacher;
import com.dsm.nms.domain.teacher.facade.TeacherFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {

    private final ImageFacade imageFacade;
    private final NoticeFacade noticeFacade;
    private final TeacherFacade teacherFacade;
    private final NoticeRepository noticeRepository;

    @Override
    @Transactional
    public void registerNotice(RegisterNoticeRequest noticeRequest, List<MultipartFile> images) {
        Teacher teacher = teacherFacade.getCurrentTeacher();
        Notice notice = noticeRepository.save(new Notice(noticeRequest, teacher));

        imageFacade.addImages(notice, images);
        noticeFacade.addTargetField(notice, noticeRequest.getTags());

    }

    @Override
    @Transactional
    public void modifyNotice(Integer noticeId, ModifyNoticeRequest noticeRequest, List<Map<Integer, MultipartFile>> images) {

        Notice findNotice = noticeRepository.findById(noticeId)
                .map(s -> s.updateTitleAndContent(noticeRequest))
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        imageFacade.modifyImages(findNotice, images);
    }

}

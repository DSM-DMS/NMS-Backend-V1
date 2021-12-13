package com.dsm.nms.domain.notice.service;

import com.dsm.nms.domain.comment.facade.CommentFacade;
import com.dsm.nms.domain.image.facade.ImageFacade;
import com.dsm.nms.domain.notice.api.dto.request.ModifyNoticeRequest;
import com.dsm.nms.domain.notice.api.dto.request.RegisterNoticeRequest;
import com.dsm.nms.domain.notice.api.dto.response.SchoolResponse;
import com.dsm.nms.domain.notice.api.dto.response.SuburbResponse;
import com.dsm.nms.domain.notice.entity.Notice;
import com.dsm.nms.domain.notice.entity.noticetarget.NoticeTarget;
import com.dsm.nms.domain.notice.entity.target.Target;
import com.dsm.nms.domain.notice.entity.target.TargetTag;
import com.dsm.nms.domain.notice.exception.NoticeNotFoundException;
import com.dsm.nms.domain.notice.exception.TargetNotFoundException;
import com.dsm.nms.domain.notice.facade.NoticeFacade;
import com.dsm.nms.domain.notice.repository.DevEventRepository;
import com.dsm.nms.domain.notice.repository.NoticeRepository;
import com.dsm.nms.domain.notice.repository.NoticeTargetRepository;
import com.dsm.nms.domain.notice.repository.TargetRepository;
import com.dsm.nms.domain.star.facade.StarFacade;
import com.dsm.nms.domain.teacher.entity.Teacher;
import com.dsm.nms.domain.teacher.facade.TeacherFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {

    private final StarFacade starFacade;
    private final ImageFacade imageFacade;
    private final NoticeFacade noticeFacade;
    private final CommentFacade commentFacade;
    private final TeacherFacade teacherFacade;
    private final NoticeRepository noticeRepository;
    private final TargetRepository targetRepository;
    private final DevEventRepository devEventRepository;
    private final NoticeTargetRepository noticeTargetRepository;

    @Override
    @Transactional
    public void registerNotice(RegisterNoticeRequest noticeRequest, List<MultipartFile> images) {
        Teacher teacher = teacherFacade.getCurrentTeacher();
        Notice notice = noticeRepository.save(new Notice(noticeRequest, teacher));

        if(images != null) {
            imageFacade.addImages(notice, images);
        }

        noticeFacade.addTargetTags(notice, noticeRequest.getTags());
    }

    @Override
    @Transactional
    public void modifyNotice(Integer noticeId, ModifyNoticeRequest noticeRequest, List<MultipartFile> images) {
        Notice findNotice = noticeRepository.findById(noticeId)
                .map(s -> s.updateTitleAndContent(noticeRequest))
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        if(images != null) {
            imageFacade.modifyImages(findNotice, images);
        }

    }

    @Override
    @Transactional
    public void removeNotice(Integer noticeId) {
        Notice notice = noticeFacade.getByNoticeId(noticeId);
        imageFacade.removeImages(notice);
        noticeRepository.delete(notice);
    }

    @Override
    @Transactional(readOnly = true)
    public SchoolResponse getAllNotices() {
        Integer count = noticeFacade.getCounts(noticeRepository.count());

        List<SchoolResponse.notice> notices =  noticeRepository.findAll().stream()
                .map(this::buildNotice)
                .collect(toList());

        return new SchoolResponse(count, notices);
    }

    @Override
    @Transactional(readOnly = true)
    public SchoolResponse.notice getNotice(Integer noticeId) {
        return noticeRepository.findById(noticeId)
                .map(this::buildNotice)
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> getNoticeToTarget(String target) {
        if (!target.equals(TargetTag.SUBURBS.toString())) {
            Target targetTag = targetRepository.findByTargetTag(TargetTag.valueOf(target))
                    .orElseThrow(() -> TargetNotFoundException.EXCEPTION);

            Integer count = noticeTargetRepository.countByTarget(targetTag);

            List<SchoolResponse.notice> notices = targetTag.getNotices().stream()
                    .map(NoticeTarget::getNotice)
                    .map(this::buildNotice)
                    .collect(toList());

            return new ResponseEntity<>(new SchoolResponse(count, notices), HttpStatus.OK);
        }

        Integer count = Math.toIntExact(devEventRepository.count());

        return new ResponseEntity<>(new SuburbResponse(count, devEventRepository.findAll()), HttpStatus.OK);
    }

    private SchoolResponse.notice buildNotice(Notice notice) {
        return SchoolResponse.notice.builder()
                .noticeId(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .writer(SchoolResponse.writer.builder()
                        .id(notice.getTeacher().getId())
                        .name(notice.getTeacher().getName())
                        .profileUrl(notice.getTeacher().getProfileUrl())
                        .build())
                .targets(noticeFacade.getTargetTags(notice))
                .createdDate(notice.getCreatedDate())
                .updatedDate(notice.getUpdatedDate())
                .images(imageFacade.getNoticeImages(notice))
                .isStar(starFacade.checkIsStar(notice))
                .starCount(starFacade.getStarCount(notice))
                .commentCount(commentFacade.getCommentCount(notice))
                .comments(commentFacade.getComments(notice))
                .build();
    }

}

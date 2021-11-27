package com.dsm.nms.domain.notice.service;

import com.dsm.nms.domain.comment.entity.Comment;
import com.dsm.nms.domain.comment.facade.CommentFacade;
import com.dsm.nms.domain.comment.repository.CommentRepository;
import com.dsm.nms.domain.image.facade.ImageFacade;
import com.dsm.nms.domain.notice.api.dto.request.ModifyNoticeRequest;
import com.dsm.nms.domain.notice.api.dto.request.RegisterNoticeRequest;
import com.dsm.nms.domain.notice.api.dto.response.NoticeResponse;
import com.dsm.nms.domain.notice.entity.Notice;
import com.dsm.nms.domain.notice.exception.NoticeNotFoundException;
import com.dsm.nms.domain.notice.facade.NoticeFacade;
import com.dsm.nms.domain.notice.repository.NoticeRepository;
import com.dsm.nms.domain.reply.repository.ReplyRepository;
import com.dsm.nms.domain.star.facade.StarFacade;
import com.dsm.nms.domain.teacher.entity.Teacher;
import com.dsm.nms.domain.teacher.facade.TeacherFacade;
import lombok.RequiredArgsConstructor;
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
    private final ReplyRepository replyRepository;
    private final NoticeRepository noticeRepository;
    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public void registerNotice(RegisterNoticeRequest noticeRequest, List<MultipartFile> images) {
        Teacher teacher = teacherFacade.getCurrentTeacher();
        Notice notice = noticeRepository.save(new Notice(noticeRequest, teacher));

        imageFacade.addImages(notice, images);
        noticeFacade.addTargetTags(notice, noticeRequest.getTags());
    }

    @Override
    @Transactional
    public void modifyNotice(Integer noticeId, ModifyNoticeRequest noticeRequest, List<MultipartFile> images) {
        Notice findNotice = noticeRepository.findById(noticeId)
                .map(s -> s.updateTitleAndContent(noticeRequest))
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        imageFacade.modifyImages(findNotice, images);
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
    public NoticeResponse getAllNotices() {
        Integer count = getCounts(noticeRepository.count());

        List<NoticeResponse.notice> notices =  noticeRepository.findAll().stream()
                .map(notice -> {
                    return NoticeResponse.notice.builder()
                            .noticeId(notice.getId())
                            .title(notice.getTitle())
                            .content(notice.getContent())
                            .writer(NoticeResponse.writer.builder()
                                    .name(notice.getWriter().getName())
                                    .profileUrl(notice.getWriter().getProfileUrl())
                                    .build())
                            .targets(noticeFacade.getTargetTags(notice))
                            .createdDate(notice.getCreatedDate())
                            .updatedDate(notice.getUpdatedDate())
                            .images(imageFacade.getNoticeImages(notice))
                            .isStar(starFacade.checkIsStar(notice))
                            .commentCount(getCommentCount(notice))
                            .comments(commentFacade.getComments(notice))
                            .build();
                })
                .collect(toList());

        return new NoticeResponse(count, notices);
    }

    private Integer getCounts(long count) {
        return Math.toIntExact(count);
    }

    private Integer getCommentCount(Notice notice) {
        int commentCounts = getCounts(commentRepository.countByNoticeId(notice.getId()));
        int replyCounts = notice.getComments().stream()
                .map(Comment::getId)
                .map(replyRepository::countByCommentId)
                .mapToInt(this::getCounts)
                .sum();
        
        return commentCounts + replyCounts;
    }

}

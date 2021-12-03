package com.dsm.nms.domain.comment.facade;

import com.dsm.nms.domain.comment.entity.Comment;
import com.dsm.nms.domain.comment.exception.CommentNotFoundException;
import com.dsm.nms.domain.comment.repository.CommentRepository;
import com.dsm.nms.domain.notice.api.dto.response.NoticeResponse;
import com.dsm.nms.domain.notice.entity.Notice;
import com.dsm.nms.domain.notice.facade.NoticeFacade;
import com.dsm.nms.domain.reply.facade.ReplyFacade;
import com.dsm.nms.global.entity.Writer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Component
public class CommentFacade {

    private final ReplyFacade replyFacade;
    private final NoticeFacade noticeFacade;
    private final CommentRepository commentRepository;

    public List<NoticeResponse.comment> getComments(Notice notice) {
        return notice.getComments().stream()
                .map(comment -> NoticeResponse.comment.builder()
                        .id(comment.getId())
                        .writer(NoticeResponse.writer.builder()
                                .name(comment.getWriter().getName())
                                .profileUrl(comment.getWriter().getProfileUrl())
                                .build())
                        .content(comment.getContent())
                        .createdDate(comment.getCreatedDate())
                        .replies(replyFacade.getReplies(comment))
                        .build())
                .collect(toList());
    }

    public void createComment(Integer noticeId, String content, Writer writer) {
        Notice notice = noticeFacade.getByNoticeId(noticeId);
        commentRepository.save(new Comment(notice, content, writer));
    }

    public Comment getById(Integer id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> CommentNotFoundException.EXCEPTION);
    }

    public void removeComment(Integer commentId) {
        commentRepository.delete(
                getById(commentId)
        );
    }

}

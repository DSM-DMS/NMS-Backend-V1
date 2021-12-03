package com.dsm.nms.domain.reply.facade;

import com.dsm.nms.domain.comment.entity.Comment;
import com.dsm.nms.domain.comment.exception.CommentNotFoundException;
import com.dsm.nms.domain.comment.facade.CommentFacade;
import com.dsm.nms.domain.comment.repository.CommentRepository;
import com.dsm.nms.domain.notice.api.dto.response.NoticeResponse;
import com.dsm.nms.domain.reply.entity.Reply;
import com.dsm.nms.domain.reply.exception.ReplyNotFoundException;
import com.dsm.nms.domain.reply.repository.ReplyRepository;
import com.dsm.nms.global.entity.Writer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Component
public class ReplyFacade {

    private final ReplyRepository replyRepository;
    private final CommentFacade commentFacade;

    public List<NoticeResponse.reply> getReplies(Comment comment) {
        return comment.getReplies().stream()
                .map(reply -> NoticeResponse.reply.builder()
                        .id(reply.getId())
                        .writer(NoticeResponse.writer.builder()
                                .name(reply.getWriter().getName())
                                .profileUrl(reply.getWriter().getProfileUrl())
                                .build())
                        .content(reply.getContent())
                        .createdDate(reply.getCreatedDate())
                        .build())
                .collect(toList());
    }

    public void createReply(Integer commentId, String content, Writer writer) {
        Comment comment = commentFacade.getById(commentId);
        replyRepository.save(new Reply(comment, content, writer));
    }

    public Reply getById(Integer id) {
        return replyRepository.findById(id)
                .orElseThrow(() -> ReplyNotFoundException.EXCEPTION);
    }

    public void removeReply(Integer replyId) {
        replyRepository.delete(
                getById(replyId)
        );
    }

}

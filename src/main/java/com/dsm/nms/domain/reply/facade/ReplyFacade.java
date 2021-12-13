package com.dsm.nms.domain.reply.facade;

import com.dsm.nms.domain.comment.entity.Comment;
import com.dsm.nms.domain.comment.exception.CommentNotFoundException;
import com.dsm.nms.domain.comment.repository.CommentRepository;
import com.dsm.nms.domain.notice.api.dto.response.SchoolResponse;
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

    private final CommentRepository commentRepository;
    private final ReplyRepository replyRepository;

    public List<SchoolResponse.reply> getReplies(Comment comment) {
        return comment.getReplies().stream()
                .map(reply -> SchoolResponse.reply.builder()
                        .id(reply.getId())
                        .writer(SchoolResponse.writer.builder()
                                .id(reply.getWriter().getId())
                                .name(reply.getWriter().getName())
                                .profileUrl(reply.getWriter().getProfileUrl())
                                .build())
                        .content(reply.getContent())
                        .createdDate(reply.getCreatedDate())
                        .build())
                .collect(toList());
    }

    public Integer getReplyCount(Integer commentId) {
        return replyRepository.countByCommentId(commentId);
    }

    public void createReply(Integer commentId, String content, Writer writer) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> CommentNotFoundException.EXCEPTION);
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

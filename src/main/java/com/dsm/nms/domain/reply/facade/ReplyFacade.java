package com.dsm.nms.domain.reply.facade;

import com.dsm.nms.domain.comment.entity.Comment;
import com.dsm.nms.domain.comment.facade.CommentFacade;
import com.dsm.nms.domain.notice.api.dto.response.NoticeResponse;
import com.dsm.nms.domain.reply.entity.Reply;
import com.dsm.nms.domain.reply.repository.ReplyRepository;
import com.dsm.nms.domain.teacher.entity.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Component
public class ReplyFacade {

    private final CommentFacade commentFacade;
    private final ReplyRepository replyRepository;

    public List<NoticeResponse.reply> getReplies(Comment comment) {
        return comment.getReplies().stream()
                .map(reply -> {
                    return NoticeResponse.reply.builder()
                            .id(reply.getId())
                            .writer(NoticeResponse.writer.builder()
                                    .name(reply.getWriter().getName())
                                    .profileUrl(reply.getWriter().getProfileUrl())
                                    .build())
                            .content(reply.getContent())
                            .createdDate(reply.getCreatedDate())
                            .build();
                })
                .collect(toList());
    }

    public void addReply(Integer commentId, String content, Teacher teacher) {
        replyRepository.save(new Reply(commentFacade.getById(commentId), content, teacher));
    }

}

package com.dsm.nms.domain.reply.facade;

import com.dsm.nms.domain.comment.entity.Comment;
import com.dsm.nms.domain.notice.api.dto.response.NoticeResponse;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class ReplyFacade {

    public List<NoticeResponse.reply> getReplies(Comment comment) {
        return comment.getReplies().stream()
                .map(reply -> {
                    return NoticeResponse.reply.builder()
                            .id(reply.getId())
                            .writer(NoticeResponse.writer.builder()
                                    .name(reply.getTeacher().getName())
                                    .profileUrl(reply.getTeacher().getProfileUrl())
                                    .build())
                            .content(reply.getContent())
                            .createdDate(reply.getCreatedDate())
                            .build();
                })
                .collect(toList());
    }

}

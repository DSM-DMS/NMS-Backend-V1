package com.dsm.nms.domain.comment.facade;

import com.dsm.nms.domain.notice.api.dto.response.NoticeResponse;
import com.dsm.nms.domain.notice.entity.Notice;
import com.dsm.nms.domain.reply.facade.ReplyFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Component
public class CommentFacade {

    private final ReplyFacade replyFacade;

    public List<NoticeResponse.comment> getComments(Notice notice) {
        return notice.getComments().stream()
                .map(comment -> {
                    return NoticeResponse.comment.builder()
                            .id(comment.getId())
                            .writer(NoticeResponse.writer.builder()
                                    .name(comment.getStudent().getName())
                                    .profileUrl(comment.getStudent().getProfileUrl())
                                    .build())
                            .content(comment.getContent())
                            .createdDate(comment.getCreatedDate())
                            .replies(replyFacade.getReplies(comment))
                            .build();
                })
                .collect(toList());
    }

}

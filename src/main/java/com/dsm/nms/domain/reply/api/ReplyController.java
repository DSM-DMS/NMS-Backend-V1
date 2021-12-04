package com.dsm.nms.domain.reply.api;

import com.dsm.nms.domain.reply.api.dto.request.ReplyRequest;
import com.dsm.nms.domain.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/reply")
@RestController
public class ReplyController {

    private final ReplyService replyService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addReply(@RequestParam("comment-id") Integer commentId, @RequestBody ReplyRequest replyRequest) {
        replyService.addReply(commentId, replyRequest.getContent());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void removeReply(@RequestParam("reply-id") Integer replyId) {
        replyService.removeReply(replyId);
    }

}

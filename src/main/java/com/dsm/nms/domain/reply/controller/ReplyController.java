package com.dsm.nms.domain.reply.controller;

import com.dsm.nms.domain.reply.controller.dto.request.ReplyRequest;
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
    public void addReply(@RequestParam Integer commentId, @RequestBody ReplyRequest replyRequest) {
        replyService.addReply(commentId, replyRequest.getContent());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void removeReply(@RequestParam Integer replyId) {
        replyService.removeReply(replyId);
    }

}

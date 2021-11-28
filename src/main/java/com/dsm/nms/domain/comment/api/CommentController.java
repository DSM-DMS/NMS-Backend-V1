package com.dsm.nms.domain.comment.api;

import com.dsm.nms.domain.comment.api.dto.request.CommentRequest;
import com.dsm.nms.domain.comment.service.CommentService;
import com.dsm.nms.global.security.auth.StudentDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/comment")
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void comment(@RequestParam("notice-id") int noticeId,
                        @RequestBody @Valid CommentRequest commentRequest) {

        commentService.comment(noticeId, commentRequest);
    }

    @DeleteMapping("/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delComment(@RequestParam("comment-id") int commentId) {
        commentService.delComment(commentId);
    }
}

package com.dsm.nms.domain.comment.service;

import com.dsm.nms.domain.comment.api.dto.request.CommentRequest;

public interface CommentService {
    void comment(int noticeId, CommentRequest commentRequest);
    void delComment(int commentId);
}

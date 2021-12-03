package com.dsm.nms.domain.comment.service;

public interface CommentService {
    void addComment(Integer noticeId, String content);
    void removeComment(Integer commentId);
}

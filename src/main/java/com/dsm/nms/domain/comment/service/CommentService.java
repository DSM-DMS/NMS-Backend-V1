package com.dsm.nms.domain.comment.service;

public interface CommentService {
    void addComment(int noticeId, String content);
    void removeComment(int commentId);
}

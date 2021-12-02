package com.dsm.nms.domain.reply.service;

public interface ReplyService {
    void addReply(Integer commentId, String content);
    void removeReply(Integer replyId);
}

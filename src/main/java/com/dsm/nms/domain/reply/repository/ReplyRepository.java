package com.dsm.nms.domain.reply.repository;

import com.dsm.nms.domain.reply.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    Integer countByCommentId(Integer commentId);
}

package com.dsm.nms.domain.comment.repository;

import com.dsm.nms.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Integer countByNoticeId(Integer noticeId);
}

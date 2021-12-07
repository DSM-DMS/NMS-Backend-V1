package com.dsm.nms.domain.notice.repository;

import com.dsm.nms.domain.notice.entity.Notice;
import com.dsm.nms.domain.notice.entity.noticetarget.NoticeTarget;
import com.dsm.nms.domain.notice.entity.noticetarget.NoticeTargetId;
import com.dsm.nms.domain.notice.entity.target.Target;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeTargetRepository extends JpaRepository<NoticeTarget, NoticeTargetId> {
    List<NoticeTarget> findByNoticeId(Integer noticeId);
    Integer countByTarget(Target target);
}

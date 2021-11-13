package com.dsm.nms.domain.notice.repository;

import com.dsm.nms.domain.notice.entity.noticetarget.NoticeTarget;
import com.dsm.nms.domain.notice.entity.noticetarget.NoticeTargetId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeTargetRepository extends JpaRepository<NoticeTarget, NoticeTargetId> {
}

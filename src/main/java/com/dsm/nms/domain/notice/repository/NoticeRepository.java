package com.dsm.nms.domain.notice.repository;

import com.dsm.nms.domain.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
}

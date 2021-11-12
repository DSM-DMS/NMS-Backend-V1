package com.dsm.nms.domain.notice.repository;

import com.dsm.nms.domain.notice.entity.target.Target;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TargetRepository extends JpaRepository<Target, Integer> {
}

package com.dsm.nms.domain.notice.repository;

import com.dsm.nms.domain.notice.entity.target.Target;
import com.dsm.nms.domain.notice.entity.target.TargetTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TargetRepository extends JpaRepository<Target, Integer> {
    Optional<Target> findByTargetTag(TargetTag tag);
}

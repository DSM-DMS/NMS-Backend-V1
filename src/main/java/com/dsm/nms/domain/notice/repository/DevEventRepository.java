package com.dsm.nms.domain.notice.repository;

import com.dsm.nms.domain.notice.entity.DevEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DevEventRepository extends JpaRepository<DevEvent, Integer> {
}

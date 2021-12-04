package com.dsm.nms.domain.notice.repository;

import com.dsm.nms.domain.notice.entity.Notice;
import com.dsm.nms.domain.teacher.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    List<Notice> findByTeacher(Teacher teacher);
}

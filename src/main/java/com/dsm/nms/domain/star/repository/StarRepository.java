package com.dsm.nms.domain.star.repository;

import com.dsm.nms.domain.notice.entity.Notice;
import com.dsm.nms.domain.star.entity.Star;
import com.dsm.nms.domain.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StarRepository extends JpaRepository<Star, Integer> {
    Optional<Star> findByNoticeId(Integer noticeId);
    Optional<Star> findByNoticeAndStudent(Notice notice, Student student);
    List<Star> findByStudent(Student student);
}

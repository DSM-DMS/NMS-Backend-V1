package com.dsm.nms.domain.teacher.repository;

import com.dsm.nms.domain.teacher.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Optional<Teacher> findByUsername(String username);
    Optional<Teacher> findByEmail(String email);
    Optional<Teacher> findByUsernameOrEmail(String username, String email);

}

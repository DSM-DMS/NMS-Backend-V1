package com.dsm.nms.domain.student.repository;

import com.dsm.nms.domain.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByEmail(String email);
    Optional<Student> findByNickname(String nickname);
}

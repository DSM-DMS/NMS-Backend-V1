package com.dsm.nms.domain.student.facade;

import com.dsm.nms.domain.student.entity.Student;
import com.dsm.nms.domain.student.exception.StudentAlreadyExistsException;
import com.dsm.nms.domain.student.repository.StudentRepository;
import com.dsm.nms.domain.teacher.exception.TeacherNotFouncException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentFacade {

    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;

    public boolean isAlreadyExists(String email) {
        if(studentRepository.findByEmail(email).isPresent())
            throw StudentAlreadyExistsException.EXCEPTION;

        return true;
    }

    public Student getByEmail(String email) {
        return studentRepository.findByEmail(email)
                .orElseThrow(() -> TeacherNotFouncException.EXCEPTION);
    }

    public String getPasswordByEmail(String email) {
        return passwordEncoder.encode(getByEmail(email).getPassword());
    }

}

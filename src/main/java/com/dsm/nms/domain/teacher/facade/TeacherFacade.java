package com.dsm.nms.domain.teacher.facade;

import com.dsm.nms.domain.teacher.entity.Teacher;
import com.dsm.nms.domain.teacher.exception.TeacherAlreadyExistsException;
import com.dsm.nms.domain.teacher.exception.TeacherNotFouncException;
import com.dsm.nms.domain.teacher.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherFacade {

    private final PasswordEncoder passwordEncoder;
    private final TeacherRepository teacherRepository;

    public boolean isAlreadyExists(String email) {
        if(teacherRepository.findByEmail(email).isPresent())
            throw TeacherAlreadyExistsException.EXCEPTION;

        return true;
    }

    public Teacher getByEmail(String email) {
        return teacherRepository.findByEmail(email)
                .orElseThrow(() -> TeacherNotFouncException.EXCEPTION);
    }

    public String getPasswordByEmail(String email) {
        return passwordEncoder.encode(getByEmail(email).getPassword());
    }

}

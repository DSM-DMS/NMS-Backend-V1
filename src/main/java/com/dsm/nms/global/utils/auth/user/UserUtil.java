package com.dsm.nms.global.utils.auth.user;

import com.dsm.nms.domain.student.repository.StudentRepository;
import com.dsm.nms.domain.teacher.repository.TeacherRepository;
import com.dsm.nms.global.exception.EmailAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserUtil {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public boolean existEmailFilter(String email) {
        if(teacherRepository.existsByEmail(email) || studentRepository.existsByEmail(email))
            throw EmailAlreadyExistException.EXCEPTION;

        return true;
    }
}
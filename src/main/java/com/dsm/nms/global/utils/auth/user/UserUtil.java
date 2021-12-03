package com.dsm.nms.global.utils.auth.user;

import com.dsm.nms.domain.student.repository.StudentRepository;
import com.dsm.nms.domain.teacher.repository.TeacherRepository;
import com.dsm.nms.global.exception.EmailAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserUtil {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public void existEmailFilter(String email) {
        if(teacherRepository.findByEmail(email).isPresent() || studentRepository.findByEmail(email).isPresent())
            throw EmailAlreadyExistsException.EXCEPTION;
    }

    public Object getPrincipal() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

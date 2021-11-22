package com.dsm.nms.domain.teacher.facade;

import com.dsm.nms.domain.teacher.entity.Teacher;
import com.dsm.nms.domain.teacher.exception.TeacherAlreadyExistsException;
import com.dsm.nms.domain.teacher.exception.TeacherNotFouncException;
import com.dsm.nms.domain.teacher.exception.UsernameAlreadyExistsException;
import com.dsm.nms.domain.teacher.repository.TeacherRepository;
import com.dsm.nms.global.exception.AuthenticationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherFacade {

    private final PasswordEncoder passwordEncoder;
    private final TeacherRepository teacherRepository;

    public boolean isAlreadyExists(String email) {
        if (teacherRepository.findByEmail(email).isPresent())
            throw TeacherAlreadyExistsException.EXCEPTION;

        return true;
    }

    public Teacher getCurrentTeacher() {
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!(object instanceof Teacher))
            throw AuthenticationNotFoundException.EXCEPTION;

        return (Teacher) object;
    }
  
    public void existsUsernameFilter(String username) {
        if (teacherRepository.findByUsername(username).isPresent())
            throw UsernameAlreadyExistsException.EXCEPTION;
    }

    public Teacher getByEmail(String email) {
        return teacherRepository.findByEmail(email)
                .orElseThrow(() -> TeacherNotFouncException.EXCEPTION);
    }

    public String getPasswordByEmail(String email) {
        return passwordEncoder.encode(getByEmail(email).getPassword());
    }

}

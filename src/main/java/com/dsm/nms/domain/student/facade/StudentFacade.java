package com.dsm.nms.domain.student.facade;

import com.dsm.nms.domain.student.entity.Student;
import com.dsm.nms.domain.student.exception.NicknameAlreadyExistException;
import com.dsm.nms.domain.student.exception.StudentAlreadyExistsException;
import com.dsm.nms.domain.student.exception.StudentNotFoundException;
import com.dsm.nms.domain.student.repository.StudentRepository;
import com.dsm.nms.global.exception.AuthenticationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentFacade {

    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;

    public Student getCurrentStudent() {
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!(object instanceof Student))
            throw AuthenticationNotFoundException.EXCEPTION;

        return (Student) object;
    }

    public boolean isAlreadyExists(String email) {
        if(studentRepository.findByEmail(email).isPresent())
            throw StudentAlreadyExistsException.EXCEPTION;

        return true;
    }

    public void existsNicknameFilter(String nickname) {
        if (studentRepository.findByNickname(nickname).isPresent())
            throw NicknameAlreadyExistException.EXCEPTION;
    }

    public Student getByEmail(String email) {
        return studentRepository.findByEmail(email)
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);
    }

    public String getPasswordByEmail(String email) {
        return passwordEncoder.encode(getByEmail(email).getPassword());
    }

}

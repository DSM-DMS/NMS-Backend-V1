package com.dsm.nms.domain.student.facade;

import com.dsm.nms.domain.student.exception.StudentAlreadyExistsException;
import com.dsm.nms.domain.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentFacade {
    private final StudentRepository studentRepository;

    public boolean isAlreadyExists(String email) {
        if(studentRepository.findByEmail(email).isPresent()) {
            throw StudentAlreadyExistsException.EXCEPTION;
        }
        return true;
    }
}

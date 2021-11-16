package com.dsm.nms.domain.teacher.facade;

import com.dsm.nms.domain.teacher.exception.TeacherAlreadyExistsException;
import com.dsm.nms.domain.teacher.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherFacade {

    private final TeacherRepository teacherRepository;

    public boolean isAlreadyExists(String email) {
        if(teacherRepository.findByEmail(email).isPresent()) {
            throw TeacherAlreadyExistsException.EXCEPTION;
        }
        return true;
    }
}

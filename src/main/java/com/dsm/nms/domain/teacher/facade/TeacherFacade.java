package com.dsm.nms.domain.teacher.facade;

import com.dsm.nms.domain.teacher.entity.Teacher;
import com.dsm.nms.domain.teacher.exception.TeacherAlreadyExistsException;
import com.dsm.nms.domain.teacher.exception.TeacherNotFoundException;
import com.dsm.nms.domain.teacher.exception.UsernameAlreadyExistsException;
import com.dsm.nms.domain.teacher.repository.TeacherRepository;
import com.dsm.nms.global.exception.AuthenticationNotFoundException;
import com.dsm.nms.global.utils.auth.user.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class TeacherFacade {

    private final PasswordEncoder passwordEncoder;
    private final TeacherRepository teacherRepository;
    private final UserUtil userUtil;

    private static final String pattern = "^[\\w\\.-]{1,64}@[\\w\\.-]{1,252}\\.\\w{2,4}$";

    public boolean isAlreadyExists(String email) {
        if (teacherRepository.findByEmail(email).isPresent())
            throw TeacherAlreadyExistsException.EXCEPTION;

        return true;
    }

    public Teacher getCurrentTeacher() {
        Object principal = userUtil.getPrincipal();

        if(!(principal instanceof Teacher))
            throw AuthenticationNotFoundException.EXCEPTION;

        return (Teacher) principal;
    }
  
    public void existsUsernameFilter(String username) {
        if (teacherRepository.findByUsername(username).isPresent())
            throw UsernameAlreadyExistsException.EXCEPTION;
    }

    public Teacher getByEmail(String email) {
        return teacherRepository.findByEmail(email)
                .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);
    }

    public Teacher getByUsername(String username) {
        return teacherRepository.findByUsername(username)
                .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);
    }

    public String getPasswordByEmail(String email) {
        return passwordEncoder.encode(getByEmail(email).getPassword());
    }

    public Teacher getById(String id) {
        if (Pattern.matches(pattern, id))
            return getByEmail(id);

        return getByUsername(id);
    }

}

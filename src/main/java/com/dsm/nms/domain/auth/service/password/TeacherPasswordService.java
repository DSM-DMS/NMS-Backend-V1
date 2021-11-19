package com.dsm.nms.domain.auth.service.password;

import com.dsm.nms.domain.auth.api.dto.request.PasswordRequest;
import com.dsm.nms.domain.teacher.exception.TeacherNotFouncException;
import com.dsm.nms.domain.teacher.facade.TeacherFacade;
import com.dsm.nms.global.exception.InvalidPasswordException;
import com.dsm.nms.global.utils.password.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TeacherPasswordService implements PasswordService {

    private final PasswordUtil passwordUtil;
    private final TeacherFacade teacherFacade;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void verifyPassword(PasswordRequest passwordRequest) {
        if(passwordUtil.checkPassword(
                teacherFacade.getPasswordByEmail(passwordRequest.getEmail()), passwordRequest.getPassword()
        )) {
            throw InvalidPasswordException.EXCEPTION;
        }
    }

    @Override
    public void changePassword(PasswordRequest passwordRequest) {
        Optional.of(
                teacherFacade.getByEmail(passwordRequest.getEmail())
        )
                .map(teacher -> teacher.updatePassword(passwordEncoder.encode(passwordRequest.getPassword())))
                .orElseThrow(() -> TeacherNotFouncException.EXCEPTION);
    }

}

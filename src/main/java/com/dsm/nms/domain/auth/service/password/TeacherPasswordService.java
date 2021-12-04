package com.dsm.nms.domain.auth.service.password;

import com.dsm.nms.domain.auth.api.dto.request.PasswordRequest;
import com.dsm.nms.domain.auth.entity.PasswordCertification;
import com.dsm.nms.domain.auth.facade.PasswordCertificationFacade;
import com.dsm.nms.domain.auth.repository.PasswordCertificationRepository;
import com.dsm.nms.domain.teacher.entity.Teacher;
import com.dsm.nms.domain.teacher.exception.TeacherNotFoundException;
import com.dsm.nms.domain.teacher.facade.TeacherFacade;
import com.dsm.nms.global.exception.InvalidPasswordException;
import com.dsm.nms.global.utils.auth.password.PasswordUtil;
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
    private final PasswordCertificationFacade passwordCertificationFacade;

    @Override
    @Transactional
    public void certifyPassword(PasswordRequest passwordRequest) {
        if(!passwordUtil.checkPassword(
                teacherFacade.getPasswordByEmail(passwordRequest.getEmail()), passwordRequest.getPassword()
        )) {
            throw InvalidPasswordException.EXCEPTION;
        }
        new PasswordCertification(passwordRequest.getEmail());
    }

    @Override
    public void changePassword(PasswordRequest passwordRequest) {

        Optional.of(
                teacherFacade.getByEmail(passwordRequest.getEmail())
        )
                .filter(teacher -> passwordCertificationFacade.getByEmail(teacher.getEmail()).isCertified())
                .map(teacher -> teacher.updatePassword(passwordEncoder.encode(passwordRequest.getPassword())))
                .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);
    }

}

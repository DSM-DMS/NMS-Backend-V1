package com.dsm.nms.domain.auth.service.password;

import com.dsm.nms.domain.auth.api.dto.request.PasswordRequest;
import com.dsm.nms.domain.student.facade.StudentFacade;
import com.dsm.nms.global.exception.InvalidPasswordException;
import com.dsm.nms.global.utils.password.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StudentPasswordService implements PasswordService {

    private final PasswordUtil passwordUtil;
    private final StudentFacade studentFacade;

    @Override
    @Transactional
    public void verifyPassword(PasswordRequest passwordRequest) {
        if(passwordUtil.checkPassword(
                studentFacade.getPasswordByEmail(passwordRequest.getEmail()), passwordRequest.getPassword()
        )) {
            throw InvalidPasswordException.EXCEPTION;
        }
    }

}

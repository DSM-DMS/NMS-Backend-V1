package com.dsm.nms.domain.auth.service;

import com.dsm.nms.domain.auth.api.dto.SendCodeRequest;
import com.dsm.nms.domain.auth.entity.AuthCode;
import com.dsm.nms.domain.auth.entity.AuthCodeLimit;
import com.dsm.nms.domain.auth.facade.AuthCodeFacade;
import com.dsm.nms.domain.auth.repository.AuthCodeLimitRepository;
import com.dsm.nms.domain.auth.repository.AuthCodeRepository;
import com.dsm.nms.domain.student.facade.StudentFacade;
import com.dsm.nms.domain.teacher.facade.TeacherFacade;
import com.dsm.nms.global.exception.ExpiredTokenException;
import com.dsm.nms.global.utils.aws.ses.SesUtils;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class AuthCodeServiceImpl implements AuthCodeService{

    private final SesUtils sesUtils;
    private final AuthCodeRepository authCodeRepository;
    private final AuthCodeLimitRepository authCodeLimitRepository;
    private final AuthCodeFacade authCodeFacade;
    private final StudentFacade studentFacade;
    private final TeacherFacade teacherFacade;

    @Override
    @Transactional
    public void sendCode(SendCodeRequest sendCodeRequest) {

        String email = sendCodeRequest.getEmail();
        String code = RandomString.make(6);

        authCodeRepository.findById(email)
                .filter(s -> authCodeFacade.isLimit(email))
                .filter(s -> teacherFacade.isAlreadyExists(email))
                .filter(s -> studentFacade.isAlreadyExists(email))
                .map(authCode -> authCode.updateAuthCode(code))
                .flatMap(authCodeLimitRepository::findById)
                .map(AuthCodeLimit::plusCount)
                .map(authCodeLimitRepository::save)
                .orElseGet(() -> authCodeFacade.newAuthCode(email, code));

        sesUtils.sendEmail("example", email, code);
    }

}

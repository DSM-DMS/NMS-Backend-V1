package com.dsm.nms.domain.auth.service;

import com.dsm.nms.domain.auth.api.dto.request.SendCodeRequest;
import com.dsm.nms.domain.auth.api.dto.request.CertifyCodeRequest;
import com.dsm.nms.domain.auth.entity.AuthCode;
import com.dsm.nms.domain.auth.entity.AuthCodeLimit;
import com.dsm.nms.domain.auth.exception.InvalidAuthCodeException;
import com.dsm.nms.domain.auth.facade.AuthCodeFacade;
import com.dsm.nms.domain.auth.repository.AuthCodeLimitRepository;
import com.dsm.nms.domain.auth.repository.AuthCodeRepository;
import com.dsm.nms.global.utils.aws.ses.SesUtil;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthCodeServiceImpl implements AuthCodeService{

    private final SesUtil sesUtil;
    private final AuthCodeRepository authCodeRepository;
    private final AuthCodeLimitRepository authCodeLimitRepository;
    private final AuthCodeFacade authCodeFacade;

    @Override
    @Async
    @Transactional
    public void sendCode(SendCodeRequest sendCodeRequest) {

        String email = sendCodeRequest.getEmail();
        String code = RandomString.make(6);

        authCodeRepository.findById(email)
                .filter(s -> authCodeFacade.checkSendFilter(email))
                .filter(s -> authCodeFacade.alreadyCertifiedFilter((s.isCertified())))
                .map(authCode -> authCode.updateAuthCode(code))
                .map(authCodeRepository::save)
                .map(AuthCode::getEmail)
                .flatMap(authCodeLimitRepository::findById)
                .map(AuthCodeLimit::plusCount)
                .map(authCodeLimitRepository::save)
                .orElseGet(() -> authCodeFacade.newAuthCode(email, code));

        sesUtil.sendEmail(email, code);
    }

    @Override
    @Transactional
    public void certifyCode(CertifyCodeRequest request) {
        String email = request.getEmail();

        authCodeFacade.getAuthCode(email)
                .filter(
                        authCode -> authCodeFacade.alreadyCertifiedFilter(
                                authCode.isCertified()
                        )
                )
                .filter(
                        authCode -> authCode.isAuthCode(
                                request.getCode()
                        )
                )
                .map(AuthCode::changeCertified)
                .map(authCodeRepository::save)
                .orElseThrow(() -> InvalidAuthCodeException.EXCEPTION);
    }

}

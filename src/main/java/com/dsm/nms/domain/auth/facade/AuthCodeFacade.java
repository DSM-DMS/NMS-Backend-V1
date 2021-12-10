package com.dsm.nms.domain.auth.facade;

import com.dsm.nms.domain.auth.entity.AuthCode;
import com.dsm.nms.domain.auth.entity.AuthCodeLimit;
import com.dsm.nms.domain.auth.exception.AuthCodeAlreadyCertifiedException;
import com.dsm.nms.domain.auth.exception.AuthCodeNotCertifiedException;
import com.dsm.nms.domain.auth.exception.AuthCodeNotFoundException;
import com.dsm.nms.domain.auth.exception.AuthCodeRequestOverLimitException;
import com.dsm.nms.domain.auth.repository.AuthCodeLimitRepository;
import com.dsm.nms.domain.auth.repository.AuthCodeRepository;
import com.dsm.nms.domain.student.facade.StudentFacade;
import com.dsm.nms.domain.teacher.facade.TeacherFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthCodeFacade {

    private final AuthCodeRepository authCodeRepository;
    private final AuthCodeLimitRepository authCodeLimitRepository;
    private final TeacherFacade teacherFacade;
    private final StudentFacade studentFacade;

    public AuthCodeLimit newAuthCode(String email, String code) {
        authCodeRepository.save(new AuthCode(email, code));
        return authCodeLimitRepository.save(new AuthCodeLimit(email));
    }

    public boolean isLimit(String email) {
        authCodeLimitRepository.findById(email)
                .filter(authCodeLimit -> authCodeLimit.getCount() < 100)
                .orElseThrow(() -> AuthCodeRequestOverLimitException.EXCEPTION);
        return true;
    }

    public boolean checkSendFilter(String email) {
        return isLimit(email) & teacherFacade.isAlreadyExists(email)
                & studentFacade.isAlreadyExists(email);
    }

    public Optional<AuthCode> getAuthCode(String email) {
        return Optional.of(authCodeRepository.findById(email)
                .orElseThrow(() -> AuthCodeNotFoundException.EXCEPTION));
    }

    public boolean alreadyCertifiedFilter(boolean certify) {
        if (certify)
            throw AuthCodeAlreadyCertifiedException.EXCEPTION;

        return true;
    }

    public void isCertifiedFilter(String email) {
        
        AuthCode authCode = getAuthCode(email)
                .filter(AuthCode::isCertified)
                .orElseThrow(() -> AuthCodeNotCertifiedException.EXCEPTION);

        authCodeRepository.delete(authCode);
    }
}
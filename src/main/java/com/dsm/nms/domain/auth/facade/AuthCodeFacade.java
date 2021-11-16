package com.dsm.nms.domain.auth.facade;

import com.dsm.nms.domain.auth.entity.AuthCode;
import com.dsm.nms.domain.auth.entity.AuthCodeLimit;
import com.dsm.nms.domain.auth.exception.AuthCodeRequestOverLimitException;
import com.dsm.nms.domain.auth.repository.AuthCodeLimitRepository;
import com.dsm.nms.domain.auth.repository.AuthCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthCodeFacade {

    private final AuthCodeRepository authCodeRepository;
    private final AuthCodeLimitRepository authCodeLimitRepository;

    public AuthCodeLimit newAuthCode(String email, String code) {
        authCodeRepository.save(new AuthCode(email, code));
        return authCodeLimitRepository.save(new AuthCodeLimit(email));
    }

    public boolean isLimit(String email) {
        authCodeLimitRepository.findById(email)
                .filter(authCodeLimit -> authCodeLimit.getCount() < 3)
                .orElseThrow(() ->AuthCodeRequestOverLimitException.EXCEPTION);
        return true;
    }
}

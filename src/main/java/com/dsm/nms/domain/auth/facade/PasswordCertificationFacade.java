package com.dsm.nms.domain.auth.facade;

import com.dsm.nms.domain.auth.entity.PasswordCertification;
import com.dsm.nms.domain.auth.exception.PasswordNotCertifiedException;
import com.dsm.nms.domain.auth.repository.PasswordCertificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PasswordCertificationFacade {

    private final PasswordCertificationRepository passwordCertificationRepository;

    public PasswordCertification getByEmail(String email) {
        return passwordCertificationRepository.findById(email)
                .orElseThrow(() -> PasswordNotCertifiedException.EXCEPTION);
    }

}

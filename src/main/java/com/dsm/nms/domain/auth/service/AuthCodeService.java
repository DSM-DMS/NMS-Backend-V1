package com.dsm.nms.domain.auth.service;

import com.dsm.nms.domain.auth.api.dto.request.SendCodeRequest;
import com.dsm.nms.domain.auth.api.dto.request.VerifyCodeRequest;

public interface AuthCodeService {
    void sendCode(SendCodeRequest sendCodeRequest);
    void verifyCode(VerifyCodeRequest verifyCodeRequest);
}

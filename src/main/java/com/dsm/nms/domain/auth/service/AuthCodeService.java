package com.dsm.nms.domain.auth.service;

import com.dsm.nms.domain.auth.api.dto.SendCodeRequest;

public interface AuthCodeService {
    void sendCode(SendCodeRequest sendCodeRequest);
}

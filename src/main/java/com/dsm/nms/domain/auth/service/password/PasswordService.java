package com.dsm.nms.domain.auth.service.password;

import com.dsm.nms.domain.auth.api.dto.request.PasswordRequest;

public interface PasswordService {
    public void verifyPassword(PasswordRequest passwordRequest);
}

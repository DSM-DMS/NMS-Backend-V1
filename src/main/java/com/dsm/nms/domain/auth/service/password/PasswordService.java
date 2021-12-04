package com.dsm.nms.domain.auth.service.password;

import com.dsm.nms.domain.auth.api.dto.request.PasswordRequest;

public interface PasswordService {
    void certifyPassword(PasswordRequest passwordRequest);
    void changePassword(PasswordRequest passwordRequest);
}

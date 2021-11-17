package com.dsm.nms.domain.auth.exception;

import com.dsm.nms.global.error.ErrorCode;
import com.dsm.nms.global.error.exception.NmsException;

public class AuthCodeNotFoundException extends NmsException {

    public static NmsException EXCEPTION =
            new AuthCodeNotFoundException();

    private AuthCodeNotFoundException() {
        super(ErrorCode.AUTH_CODE_NOT_FOUND);
    }
}

package com.dsm.nms.domain.auth.exception;

import com.dsm.nms.global.error.ErrorCode;
import com.dsm.nms.global.error.exception.NmsException;

public class AuthCodeAlreadyCertifiedException extends NmsException {

    public static NmsException EXCEPTION =
            new AuthCodeAlreadyCertifiedException();

    private AuthCodeAlreadyCertifiedException() {
        super(ErrorCode.AUTH_CODE_ALREADY_CERTIFIED);
    }
}

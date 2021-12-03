package com.dsm.nms.domain.auth.exception;

import com.dsm.nms.global.error.ErrorCode;
import com.dsm.nms.global.error.exception.NmsException;

public class AuthCodeNotCertifiedException extends NmsException {

    public static NmsException EXCEPTION =
            new AuthCodeNotCertifiedException();

    private AuthCodeNotCertifiedException() {
        super(ErrorCode.AUTH_CODE_NOT_CERTIFIED);
    }
}

package com.dsm.nms.domain.auth.exception;

import com.dsm.nms.global.error.ErrorCode;
import com.dsm.nms.global.error.exception.NmsException;

public class AuthCodeRequestOverLimitException extends NmsException {

    public static NmsException EXCEPTION =
            new AuthCodeRequestOverLimitException();

    private AuthCodeRequestOverLimitException() {
        super(ErrorCode.AUTH_CODE_REQUEST_OVER_LIMIT);
    }
}

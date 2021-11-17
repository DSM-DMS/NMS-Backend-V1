package com.dsm.nms.domain.auth.exception;

import com.dsm.nms.global.error.ErrorCode;
import com.dsm.nms.global.error.exception.NmsException;

public class InvalidAuthCodeException extends NmsException {

    public static NmsException EXCEPTION =
            new InvalidAuthCodeException();

    private InvalidAuthCodeException() {
        super(ErrorCode.INVALID_AUTH_CODE);
    }
}

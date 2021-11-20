package com.dsm.nms.global.exception;

import com.dsm.nms.global.error.ErrorCode;
import com.dsm.nms.global.error.exception.NmsException;

public class AuthenticationNotFoundException extends NmsException {

    public static NmsException EXCEPTION =
            new AuthenticationNotFoundException();

    private AuthenticationNotFoundException() {
        super(ErrorCode.AUTHENTICATION_NOT_FOUND);
    }

}

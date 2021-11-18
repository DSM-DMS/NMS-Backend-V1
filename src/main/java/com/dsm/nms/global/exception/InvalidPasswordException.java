package com.dsm.nms.global.exception;

import com.dsm.nms.global.error.ErrorCode;
import com.dsm.nms.global.error.exception.NmsException;

public class InvalidPasswordException extends NmsException {

    public static NmsException EXCEPTION =
            new InvalidPasswordException();

    private InvalidPasswordException() {
        super(ErrorCode.INVALID_PASSWORD);
    }

}

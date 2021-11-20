package com.dsm.nms.global.exception;

import com.dsm.nms.global.error.ErrorCode;
import com.dsm.nms.global.error.exception.NmsException;

public class EmailAlreadyExistException extends NmsException {

    public static NmsException EXCEPTION =
            new EmailAlreadyExistException();

    private EmailAlreadyExistException() {
        super(ErrorCode.EMAIL_ALREADY_EXISTS);
    }
}

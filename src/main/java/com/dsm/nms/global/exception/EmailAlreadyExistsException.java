package com.dsm.nms.global.exception;

import com.dsm.nms.global.error.ErrorCode;
import com.dsm.nms.global.error.exception.NmsException;

public class EmailAlreadyExistsException extends NmsException {

    public static NmsException EXCEPTION =
            new EmailAlreadyExistsException();

    private EmailAlreadyExistsException() {
        super(ErrorCode.EMAIL_ALREADY_EXISTS);
    }
}

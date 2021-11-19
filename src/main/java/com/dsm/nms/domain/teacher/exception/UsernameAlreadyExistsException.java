package com.dsm.nms.domain.teacher.exception;

import com.dsm.nms.global.error.ErrorCode;
import com.dsm.nms.global.error.exception.NmsException;

public class UsernameAlreadyExistsException extends NmsException {

    public static NmsException EXCEPTION =
            new UsernameAlreadyExistsException();

    private UsernameAlreadyExistsException() {
        super(ErrorCode.USERNAME_ALREADY_EXISTS);
    }
}

package com.dsm.nms.domain.student.exception;

import com.dsm.nms.global.error.ErrorCode;
import com.dsm.nms.global.error.exception.NmsException;

public class InvalidGradeException extends NmsException {

    public static NmsException EXCEPTION =
            new InvalidGradeException();

    private InvalidGradeException() {
        super(ErrorCode.NICKNAME_ALREADY_EXISTS);
    }
}

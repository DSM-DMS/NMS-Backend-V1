package com.dsm.nms.domain.student.exception;

import com.dsm.nms.global.error.ErrorCode;
import com.dsm.nms.global.error.exception.NmsException;

public class StudentAlreadyExistsException extends NmsException {

    public static NmsException EXCEPTION =
            new StudentAlreadyExistsException();

    private StudentAlreadyExistsException() {
        super(ErrorCode.STUDENT_ALREADY_EXISTS);
    }
}

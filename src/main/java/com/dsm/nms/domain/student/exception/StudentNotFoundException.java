package com.dsm.nms.domain.student.exception;

import com.dsm.nms.global.error.ErrorCode;
import com.dsm.nms.global.error.exception.NmsException;

public class StudentNotFoundException extends NmsException {

    public static NmsException EXCEPTION =
            new StudentNotFoundException();

    private StudentNotFoundException() {
        super(ErrorCode.STUDENT_NOT_FOUND);
    }

}

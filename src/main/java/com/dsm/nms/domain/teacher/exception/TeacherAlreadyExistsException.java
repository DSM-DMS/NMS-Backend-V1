package com.dsm.nms.domain.teacher.exception;

import com.dsm.nms.global.error.ErrorCode;
import com.dsm.nms.global.error.exception.NmsException;

public class TeacherAlreadyExistsException extends NmsException {

    public static NmsException EXCEPTION =
            new TeacherAlreadyExistsException();

    private TeacherAlreadyExistsException() {
        super(ErrorCode.TEACHER_ALREADY_EXISTS);
    }
}

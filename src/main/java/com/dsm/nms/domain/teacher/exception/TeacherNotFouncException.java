package com.dsm.nms.domain.teacher.exception;

import com.dsm.nms.global.error.ErrorCode;
import com.dsm.nms.global.error.exception.NmsException;

public class TeacherNotFouncException extends NmsException {

    public static NmsException EXCEPTION =
            new TeacherNotFouncException();

    private TeacherNotFouncException() {
        super(ErrorCode.TEACHER_NOT_FOUND);
    }

}

package com.dsm.nms.domain.teacher.exception;

import com.dsm.nms.global.error.ErrorCode;
import com.dsm.nms.global.error.exception.NmsException;

public class TeacherNotFoundException extends NmsException {

    public static NmsException EXCEPTION =
            new TeacherNotFoundException();

    private TeacherNotFoundException() {
        super(ErrorCode.TEACHER_NOT_FOUND);
    }

}

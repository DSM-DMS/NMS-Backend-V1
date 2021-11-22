package com.dsm.nms.domain.student.exception;

import com.dsm.nms.global.error.ErrorCode;
import com.dsm.nms.global.error.exception.NmsException;

public class NicknameAlreadyExistException extends NmsException {

    public static NmsException EXCEPTION =
            new NicknameAlreadyExistException();

    private NicknameAlreadyExistException() {
        super(ErrorCode.NICKNAME_ALREADY_EXISTS);
    }
}

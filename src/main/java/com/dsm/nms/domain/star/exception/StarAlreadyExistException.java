package com.dsm.nms.domain.star.exception;

import com.dsm.nms.global.error.ErrorCode;
import com.dsm.nms.global.error.exception.NmsException;

public class StarAlreadyExistException extends NmsException {

    public static NmsException EXCEPTION =
            new StarAlreadyExistException();

    private StarAlreadyExistException() {
        super(ErrorCode.STAR_ALREADY_EXISTS);
    }
}

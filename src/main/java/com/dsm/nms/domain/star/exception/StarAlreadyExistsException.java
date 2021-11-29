package com.dsm.nms.domain.star.exception;

import com.dsm.nms.global.error.ErrorCode;
import com.dsm.nms.global.error.exception.NmsException;

public class StarAlreadyExistsException extends NmsException {

    public static NmsException EXCEPTION =
            new StarAlreadyExistsException();

    private StarAlreadyExistsException() {
        super(ErrorCode.STAR_ALREADY_EXISTS);
    }
}

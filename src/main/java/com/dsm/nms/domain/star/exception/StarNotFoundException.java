package com.dsm.nms.domain.star.exception;

import com.dsm.nms.global.error.ErrorCode;
import com.dsm.nms.global.error.exception.NmsException;

public class StarNotFoundException extends NmsException {

    public static NmsException EXCEPTION =
            new StarNotFoundException();

    private StarNotFoundException() {
        super(ErrorCode.STAR_NOT_FOUND);
    }
}

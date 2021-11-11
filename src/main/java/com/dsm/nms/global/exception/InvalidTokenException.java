package com.dsm.nms.global.exception;

import com.dsm.nms.global.error.ErrorCode;
import com.dsm.nms.global.error.exception.NmsException;

public class InvalidTokenException extends NmsException {
    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}

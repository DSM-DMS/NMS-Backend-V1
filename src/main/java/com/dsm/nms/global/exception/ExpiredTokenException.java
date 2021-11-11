package com.dsm.nms.global.exception;

import com.dsm.nms.global.error.ErrorCode;
import com.dsm.nms.global.error.exception.NmsException;

public class ExpiredTokenException extends NmsException {
    public ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }
}

package com.dsm.nms.domain.notice.exception;

import com.dsm.nms.global.error.ErrorCode;
import com.dsm.nms.global.error.exception.NmsException;

public class TargetNotFoundException extends NmsException {

    public static NmsException EXCEPTION =
            new TargetNotFoundException();

    private TargetNotFoundException() {
        super(ErrorCode.TARGET_NOT_FOUND);
    }

}

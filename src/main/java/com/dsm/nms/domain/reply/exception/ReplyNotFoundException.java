package com.dsm.nms.domain.reply.exception;

import com.dsm.nms.global.error.ErrorCode;
import com.dsm.nms.global.error.exception.NmsException;

public class ReplyNotFoundException extends NmsException {

    public static NmsException EXCEPTION =
            new ReplyNotFoundException();

    private ReplyNotFoundException() {
        super(ErrorCode.REPLY_NOT_FOUND);
    }
}

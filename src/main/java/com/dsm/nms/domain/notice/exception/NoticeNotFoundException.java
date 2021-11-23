package com.dsm.nms.domain.notice.exception;

import com.dsm.nms.global.error.ErrorCode;
import com.dsm.nms.global.error.exception.NmsException;

public class NoticeNotFoundException extends NmsException {

    public static NmsException EXCEPTION =
            new NoticeNotFoundException();

    private NoticeNotFoundException() {
        super(ErrorCode.NOTICE_NOT_FOUND);
    }

}

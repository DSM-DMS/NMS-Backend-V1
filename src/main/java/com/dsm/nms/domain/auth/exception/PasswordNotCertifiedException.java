package com.dsm.nms.domain.auth.exception;

import com.dsm.nms.global.error.ErrorCode;
import com.dsm.nms.global.error.exception.NmsException;

public class PasswordNotCertifiedException extends NmsException {

    public static NmsException EXCEPTION =
            new PasswordNotCertifiedException();

    private PasswordNotCertifiedException() {
        super(ErrorCode.PASSWORD_NOT_CERTIFIED);
    }

}

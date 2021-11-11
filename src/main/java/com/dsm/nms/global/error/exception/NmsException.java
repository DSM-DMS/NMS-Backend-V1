package com.dsm.nms.global.error.exception;

import com.dsm.nms.global.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NmsException extends RuntimeException {

    private final ErrorCode errorCode;

}

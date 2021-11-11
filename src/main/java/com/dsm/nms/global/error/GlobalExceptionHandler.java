package com.dsm.nms.global.error;

import com.dsm.nms.global.error.exception.NmsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NmsException.class)
    public ResponseEntity<ErrorResponse> handleGlobal(NmsException e) {
        final ErrorCode errorCode = e.getErrorCode();
        return new ResponseEntity<>(
                new ErrorResponse(errorCode.getStatus(), errorCode.getMessage()), HttpStatus.valueOf(errorCode.getStatus())
        );
    }

}

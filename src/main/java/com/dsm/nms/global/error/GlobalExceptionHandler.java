package com.dsm.nms.global.error;

import com.dsm.nms.global.error.exception.NmsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NmsException.class)
    public ResponseEntity<ErrorResponse> handleGlobal(NmsException e) {
        final ErrorCode errorCode = e.getErrorCode();
        return new ResponseEntity<>(
                new ErrorResponse(errorCode.getMessage()), HttpStatus.valueOf(errorCode.getStatus())
        );
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> bindException(BindException e) {
        Map<String, String> errorMap = new HashMap<>();

        for (FieldError error : e.getFieldErrors()) {
            errorMap.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errorMap , HttpStatus.BAD_REQUEST);
    }
}

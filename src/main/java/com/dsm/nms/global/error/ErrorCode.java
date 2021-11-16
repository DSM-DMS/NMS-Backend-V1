package com.dsm.nms.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_TOKEN(401, "Invalid Token"),
    EXPIRED_TOKEN(401, "Expired Token"),

    INVALID_ROLE(401, "Invalid Role"),

    TEACHER_ALREADY_EXISTS(409,  "Teacher Already Exists"),
    STUDENT_ALREADY_EXISTS(409,  "Student Already Exists"),

    AUTH_CODE_REQUEST_OVER_LIMIT(429, "Auth Code Request Over Limit");
    private final int status;
    private final String message;

}

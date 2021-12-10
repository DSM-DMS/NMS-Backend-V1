package com.dsm.nms.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_TOKEN(401, "Invalid Token"),
    EXPIRED_TOKEN(401, "Expired Token"),

    INVALID_ROLE(401, "Invalid Role"),
    INVALID_PASSWORD(401, "Invalid Password"),
    AUTHENTICATION_NOT_FOUND(404, "Authentication Not Found"),

    INVALID_AUTH_CODE(401, "Invalid Auth Code"),
    AUTH_CODE_NOT_CERTIFIED(401, "Auth Code Not Certified"),
    AUTH_CODE_NOT_FOUND(404, "Auth Code Not Found"),
    AUTH_CODE_ALREADY_CERTIFIED(409,"Auth Code Already Certified"),
    AUTH_CODE_REQUEST_OVER_LIMIT(429, "Auth Code Request Over Limit"),
    PASSWORD_NOT_CERTIFIED(401, "Password Not Certified"),

    TEACHER_NOT_FOUND(404, "Teacher Not Found"),
    TEACHER_ALREADY_EXISTS(409,  "Teacher Already Exists"),
    USERNAME_ALREADY_EXISTS(409, "Username Already Exists"),

    STUDENT_NOT_FOUND(404, "Student Not Found"),
    STUDENT_ALREADY_EXISTS(409,  "Student Already Exists"),
    NICKNAME_ALREADY_EXISTS(409, "Nickname Already Exists"),
    INVALID_GRADE(401, "Invalid Grade"),

    EMAIL_ALREADY_EXISTS(409, "Email Already Exists"),

    TARGET_NOT_FOUND(404, "Target Not Found"),

    IMAGE_NOT_FOUND(404, "Image Not Found"),

    NOTICE_NOT_FOUND(404, "Notice Not Found"),

    COMMENT_NOT_FOUND(404, "Comment Not Found"),
  
    STAR_NOT_FOUND(404, "STAR NOT FOUND"),
    STAR_ALREADY_EXISTS(409, "Star Already Exists"),

    REPLY_NOT_FOUND(404, "Reply Not Found"),
    ;

    private final int status;
    private final String message;

}

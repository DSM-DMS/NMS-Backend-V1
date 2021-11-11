package com.dsm.nms.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_TOKEN(401, "Invalid Token"),
    EXPIRED_TOKEN(401, "Expired Token"),

    INVALID_ROLE(401, "Invalid Role")
    ;

    private final int status;
    private final String message;

}

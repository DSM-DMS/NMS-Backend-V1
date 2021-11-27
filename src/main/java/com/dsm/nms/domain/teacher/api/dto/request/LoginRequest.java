package com.dsm.nms.domain.teacher.api.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = "id는 Null, 공백을 허용하지 않습니다.")
    private String id;

    @NotBlank(message = "password는 Null, 공백을 허용하지 않습니다.")
    private String password;
}

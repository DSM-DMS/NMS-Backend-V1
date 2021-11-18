package com.dsm.nms.domain.auth.api;

import com.dsm.nms.domain.auth.api.dto.request.SendCodeRequest;
import com.dsm.nms.domain.auth.api.dto.request.VerifyCodeRequest;
import com.dsm.nms.domain.auth.api.dto.request.PasswordRequest;
import com.dsm.nms.domain.auth.service.AuthCodeService;
import com.dsm.nms.domain.auth.service.password.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class AuthController {

    private final AuthCodeService authCodeService;
    private final PasswordService passwordService;

    @PostMapping("/email")
    public void sendCode(@RequestBody @Valid SendCodeRequest sendCodeRequest) {
        authCodeService.sendCode(sendCodeRequest);
    }

    @PutMapping("/email")
    public void verifyCode(@RequestBody @Valid VerifyCodeRequest verifyCodeRequest) {
        authCodeService.verifyCode(verifyCodeRequest);
    }

    @PostMapping("/student/password")
    public void verifyStudentPassword(@RequestBody @Valid PasswordRequest passwordRequest) {
        passwordService.verifyPassword(passwordRequest);
    }

    @PostMapping("/teacher/password")
    @ResponseStatus(HttpStatus.CREATED)
    public void verifyTeacherPassword(@RequestBody @Valid PasswordRequest passwordRequest) {
        passwordService.verifyPassword(passwordRequest);
    }

}

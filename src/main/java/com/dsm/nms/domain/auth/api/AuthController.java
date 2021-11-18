package com.dsm.nms.domain.auth.api;

import com.dsm.nms.domain.auth.api.dto.request.PasswordRequest;
import com.dsm.nms.domain.auth.api.dto.request.SendCodeRequest;
import com.dsm.nms.domain.auth.api.dto.request.CertifyCodeRequest;
import com.dsm.nms.domain.auth.service.AuthCodeService;
import com.dsm.nms.domain.auth.service.password.StudentPasswordService;
import com.dsm.nms.domain.auth.service.password.TeacherPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class AuthController {

    private final AuthCodeService authCodeService;
    private final StudentPasswordService studentPasswordService;
    private final TeacherPasswordService teacherPasswordService;

    @PostMapping("/email")
    public void sendCode(@RequestBody @Valid SendCodeRequest sendCodeRequest) {
        authCodeService.sendCode(sendCodeRequest);
    }

feature/56-verify-password-api
    @PutMapping("/email")
    public void certifyCode(@RequestBody @Valid CertifyCodeRequest certifyCodeRequest) {
        authCodeService.certifyCode(certifyCodeRequest);

    @PostMapping("/student/password")
    public void verifyStudentPassword(@RequestBody @Valid PasswordRequest passwordRequest) {
        studentPasswordService.verifyPassword(passwordRequest);
    }

    @PostMapping("/teacher/password")
    @ResponseStatus(HttpStatus.CREATED)
    public void verifyTeacherPassword(@RequestBody @Valid PasswordRequest passwordRequest) {
        teacherPasswordService.verifyPassword(passwordRequest);
    }

}

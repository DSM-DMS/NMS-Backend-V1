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

    @PutMapping("/email")
    public void certifyCode(@RequestBody @Valid CertifyCodeRequest certifyCodeRequest) {
        authCodeService.certifyCode(certifyCodeRequest);
    }

    @PostMapping("/student/password")
    @ResponseStatus(HttpStatus.CREATED)
    public void certifyStudentPassword(@RequestBody @Valid PasswordRequest passwordRequest) {
        studentPasswordService.certifyPassword(passwordRequest);
    }

    @PostMapping("/teacher/password")
    @ResponseStatus(HttpStatus.CREATED)
    public void certifyTeacherPassword(@RequestBody @Valid PasswordRequest passwordRequest) {
        teacherPasswordService.certifyPassword(passwordRequest);
    }

    @PutMapping("/student/password")
    public void changeStudentPassword(@RequestBody @Valid PasswordRequest passwordRequest) {
        studentPasswordService.changePassword(passwordRequest);
    }

    @PutMapping("/teacher/password")
    public void changeTeacherPassword(@RequestBody @Valid PasswordRequest passwordRequest) {
        teacherPasswordService.changePassword(passwordRequest);
    }

}

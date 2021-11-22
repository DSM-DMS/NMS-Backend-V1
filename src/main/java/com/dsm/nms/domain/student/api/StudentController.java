package com.dsm.nms.domain.student.api;

import com.dsm.nms.domain.student.api.dto.request.SignUpRequest;
import com.dsm.nms.domain.student.service.StudentService;
import com.dsm.nms.global.utils.auth.dto.request.LoginRequest;
import com.dsm.nms.global.utils.auth.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/")
    public void signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        studentService.signUp(signUpRequest);
    }

}

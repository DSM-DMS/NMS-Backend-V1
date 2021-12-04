package com.dsm.nms.domain.student.api;

import com.dsm.nms.domain.student.api.dto.request.SignUpRequest;
import com.dsm.nms.domain.student.service.StudentService;
import com.dsm.nms.domain.student.api.dto.request.LoginRequest;
import com.dsm.nms.global.security.jwt.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public void signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        studentService.signUp(signUpRequest);
    }

    @PostMapping("/auth")
    public TokenResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        return studentService.login(loginRequest);
    }

    @PutMapping("/auth")
    public TokenResponse reissue(@RequestHeader(name = "X-Refresh-Token") String refresh) {
        return studentService.reissue(refresh);
    }

}

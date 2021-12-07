package com.dsm.nms.domain.student.api;

import com.dsm.nms.domain.student.api.dto.request.SignUpRequest;
import com.dsm.nms.domain.student.api.dto.response.MyPageResponse;
import com.dsm.nms.domain.student.service.StudentService;
import com.dsm.nms.domain.student.api.dto.request.LoginRequest;
import com.dsm.nms.global.security.jwt.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        studentService.signUp(signUpRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping
    public void updateStudent(@RequestPart String nickname,
                           @RequestPart(required = false) MultipartFile profile) {
        studentService.updateStudent(nickname, profile);
    }

    @GetMapping("/mypage")
    public MyPageResponse getStudentMyPage() {
        return studentService.getStudentMyPage();
    }

    @PostMapping("/auth")
    public TokenResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        return studentService.login(loginRequest);
    }

    @PutMapping("/auth")
    public TokenResponse reissue(@RequestHeader("X-Refresh-Token") String refresh) {
        return studentService.reissue(refresh);
    }

}

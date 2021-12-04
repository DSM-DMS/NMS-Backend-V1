package com.dsm.nms.domain.teacher.api;

import com.dsm.nms.domain.teacher.api.dto.request.LoginRequest;
import com.dsm.nms.domain.teacher.api.dto.response.ProfileResponse;
import com.dsm.nms.domain.teacher.service.TeacherService;
import com.dsm.nms.domain.teacher.api.dto.request.SignUpRequest;
import com.dsm.nms.global.security.jwt.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public void signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        teacherService.signUp(signUpRequest);
    }


    @PostMapping("/auth")
    public TokenResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        return teacherService.login(loginRequest);
    }

    @PutMapping("/auth")
    public TokenResponse reissue(@RequestHeader("X-Refresh-Token") String refresh) {
        return teacherService.reissue(refresh);
    }

    @GetMapping
    public ProfileResponse getTeacherProfile(@RequestParam("teacher-id") Integer teacherId) {
        return teacherService.getTeacherProfile(teacherId);
    }

}

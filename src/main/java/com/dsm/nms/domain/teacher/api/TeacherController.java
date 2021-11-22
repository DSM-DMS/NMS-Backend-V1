package com.dsm.nms.domain.teacher.api;

import com.dsm.nms.domain.teacher.service.TeacherService;
import com.dsm.nms.global.utils.auth.dto.request.TeacherSignUpRequest;
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
    @PostMapping("/auth")
    public void signUp(@RequestBody @Valid TeacherSignUpRequest teacherSignUpRequest) {
        teacherService.signUp(teacherSignUpRequest);
    }
}

package com.dsm.nms.domain.student.service;

import com.dsm.nms.global.utils.auth.dto.request.LoginRequest;
import com.dsm.nms.global.utils.auth.dto.request.StudentSignUpRequest;
import com.dsm.nms.global.utils.auth.dto.response.TokenResponse;

public interface StudentService {
    void singUp(StudentSignUpRequest studentSignUpRequest);
    TokenResponse login(LoginRequest loginRequest);
}

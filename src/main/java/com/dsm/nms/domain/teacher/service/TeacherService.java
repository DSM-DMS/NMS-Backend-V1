package com.dsm.nms.domain.teacher.service;

import com.dsm.nms.domain.teacher.api.dto.request.LoginRequest;
import com.dsm.nms.domain.teacher.api.dto.request.SignUpRequest;
import com.dsm.nms.global.security.jwt.dto.response.TokenResponse;

public interface TeacherService {
    void signUp(SignUpRequest signUpRequest);
    TokenResponse login(LoginRequest loginRequest);
    TokenResponse reissue(String refreshToken);
}

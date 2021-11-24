package com.dsm.nms.domain.student.service;

import com.dsm.nms.domain.student.api.dto.request.LoginRequest;
import com.dsm.nms.domain.student.api.dto.request.SignUpRequest;
import com.dsm.nms.global.security.jwt.dto.response.TokenResponse;

public interface StudentService {
    void signUp(SignUpRequest signUpRequest);
    TokenResponse login(LoginRequest loginRequest);
}

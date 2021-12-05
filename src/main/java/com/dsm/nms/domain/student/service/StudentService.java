package com.dsm.nms.domain.student.service;

import com.dsm.nms.domain.student.api.dto.request.LoginRequest;
import com.dsm.nms.domain.student.api.dto.request.SignUpRequest;
import com.dsm.nms.domain.student.api.dto.response.MyPageResponse;
import com.dsm.nms.global.security.jwt.dto.response.TokenResponse;
import org.springframework.web.multipart.MultipartFile;

public interface StudentService {
    void signUp(SignUpRequest signUpRequest);
    TokenResponse login(LoginRequest loginRequest);
    TokenResponse reissue(String refreshToken);
    void updateStudent(String nickname, MultipartFile profile);
    MyPageResponse getStudentMyPage();
}

package com.dsm.nms.domain.teacher.service;

import com.dsm.nms.domain.teacher.api.dto.request.LoginRequest;
import com.dsm.nms.domain.teacher.api.dto.request.SignUpRequest;
import com.dsm.nms.domain.teacher.api.dto.request.TeacherInfoRequest;
import com.dsm.nms.domain.teacher.api.dto.response.MyPageResponse;
import com.dsm.nms.domain.teacher.api.dto.response.ProfileResponse;
import com.dsm.nms.global.security.jwt.dto.response.TokenResponse;
import org.springframework.web.multipart.MultipartFile;

public interface TeacherService {
    void signUp(SignUpRequest signUpRequest);
    TokenResponse login(LoginRequest loginRequest);
    TokenResponse reissue(String refreshToken);
    ProfileResponse getTeacherProfile(Integer teacherId);
    MyPageResponse getTeacherMyPage();
    void modifyTeacherInfo(TeacherInfoRequest teacherInfoRequest, MultipartFile profile);
}

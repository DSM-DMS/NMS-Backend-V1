package com.dsm.nms.domain.teacher.service;

import com.dsm.nms.domain.teacher.api.dto.request.SignUpRequest;

public interface TeacherService {
    void signUp(SignUpRequest signUpRequest);
}

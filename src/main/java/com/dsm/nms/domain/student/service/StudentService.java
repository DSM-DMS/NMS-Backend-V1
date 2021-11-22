package com.dsm.nms.domain.student.service;

import com.dsm.nms.domain.student.api.dto.request.SignUpRequest;

public interface StudentService {
    void signUp(SignUpRequest signUpRequest);
}

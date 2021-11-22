package com.dsm.nms.domain.teacher.service;

import com.dsm.nms.global.utils.auth.dto.request.TeacherSignUpRequest;

public interface TeacherService {
    void signUp(TeacherSignUpRequest teacherSignUpRequest);
}

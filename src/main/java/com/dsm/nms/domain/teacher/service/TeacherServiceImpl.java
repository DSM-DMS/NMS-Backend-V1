package com.dsm.nms.domain.teacher.service;

import com.dsm.nms.domain.teacher.api.dto.request.SignUpRequest;
import com.dsm.nms.domain.teacher.entity.Teacher;
import com.dsm.nms.domain.teacher.facade.TeacherFacade;
import com.dsm.nms.domain.teacher.repository.TeacherRepository;
import com.dsm.nms.global.utils.auth.user.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class TeacherServiceImpl implements TeacherService{

    private final TeacherRepository teacherRepository;
    private final TeacherFacade teacherFacade;
    private final UserUtil userUtil;

    @Override
    @Transactional
    public void signUp(SignUpRequest signUpRequest) {
        teacherFacade.existsUsernameFilter(signUpRequest.getUsername());
        userUtil.existEmailFilter(signUpRequest.getEmail());

        teacherRepository.save(new Teacher(signUpRequest));
    }
}

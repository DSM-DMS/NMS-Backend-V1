package com.dsm.nms.domain.student.service;

import com.dsm.nms.domain.student.entity.Student;
import com.dsm.nms.domain.student.facade.StudentFacade;
import com.dsm.nms.domain.student.repository.StudentRepository;
import com.dsm.nms.global.utils.auth.user.UserUtil;
import com.dsm.nms.domain.student.api.dto.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentFacade studentFacade;
    private final UserUtil userUtil;

    @Override
    public void signUp(SignUpRequest signUpRequest) {
        studentFacade.existsNicknameFilter(signUpRequest.getNickname());
        userUtil.existEmailFilter(signUpRequest.getEmail());

        studentRepository.save(new Student(signUpRequest));
    }

}

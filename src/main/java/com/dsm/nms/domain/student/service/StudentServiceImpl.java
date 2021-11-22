package com.dsm.nms.domain.student.service;

import com.dsm.nms.domain.student.entity.Student;
import com.dsm.nms.domain.student.facade.StudentFacade;
import com.dsm.nms.domain.student.repository.StudentRepository;
import com.dsm.nms.global.utils.auth.user.UserUtil;
import com.dsm.nms.global.exception.InvalidPasswordException;
import com.dsm.nms.global.security.jwt.JwtTokenProvider;
import com.dsm.nms.global.utils.auth.dto.request.LoginRequest;
import com.dsm.nms.global.utils.auth.dto.request.StudentSignUpRequest;
import com.dsm.nms.global.utils.auth.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentFacade studentFacade;
    private final UserUtil userUtil;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    @Override
    public void singUp(StudentSignUpRequest studentSignUpRequest) {
        studentFacade.existNicknameFilter(studentSignUpRequest.getNickname());
        userUtil.existEmailFilter(studentSignUpRequest.getEmail());

        studentRepository.save(new Student(studentSignUpRequest));
    }

    @Override
    public TokenResponse login(LoginRequest loginRequest) {
        return Optional.of(studentFacade.getByEmail(loginRequest.getEmail()))
                .filter(
                        student -> passwordEncoder.matches(loginRequest.getPassword(), student.getPassword())
                )
                .map(
                        student -> tokenProvider.generateToken(loginRequest.getEmail(), "student")
                )
                .orElseThrow(() -> InvalidPasswordException.EXCEPTION);
    }
}

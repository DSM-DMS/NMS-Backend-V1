package com.dsm.nms.domain.student.service;

import com.dsm.nms.domain.auth.facade.AuthCodeFacade;
import com.dsm.nms.domain.image.facade.ImageFacade;
import com.dsm.nms.domain.student.entity.Student;
import com.dsm.nms.domain.student.facade.StudentFacade;
import com.dsm.nms.domain.student.repository.StudentRepository;
import com.dsm.nms.global.utils.auth.user.UserUtil;
import com.dsm.nms.global.exception.InvalidPasswordException;
import com.dsm.nms.global.security.jwt.JwtTokenProvider;
import com.dsm.nms.domain.student.api.dto.request.LoginRequest;
import com.dsm.nms.domain.student.api.dto.request.SignUpRequest;
import com.dsm.nms.global.security.jwt.dto.response.TokenResponse;
import com.dsm.nms.global.utils.aws.s3.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentFacade studentFacade;
    private final AuthCodeFacade authCodeFacade;
    private final S3Util s3Util;
    private final UserUtil userUtil;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    private static final String role = "student";

    @Override
    public void signUp(SignUpRequest signUpRequest) {

        studentFacade.existsNicknameFilter(signUpRequest.getNickname());
        userUtil.existEmailFilter(signUpRequest.getEmail());
        authCodeFacade.isCertifiedFilter(signUpRequest.getEmail());

        signUpRequest.encodePassword(passwordEncoder.encode(signUpRequest.getPassword()));
        studentRepository.save(new Student(signUpRequest));
    }

    @Override
    public TokenResponse login(LoginRequest loginRequest) {
        return Optional.of(studentFacade.getByEmail(loginRequest.getEmail()))
                .filter(
                        student -> passwordEncoder.matches(loginRequest.getPassword(), student.getPassword())
                )
                .map(
                        student -> tokenProvider.generateToken(loginRequest.getEmail(), role)
                )
                .orElseThrow(() -> InvalidPasswordException.EXCEPTION);
    }

    @Override
    public TokenResponse reissue(String refreshToken) {
        return userUtil.reissue(refreshToken, role);
    }

    @Override
    @Transactional
    public void updateStudent(String nickname, MultipartFile profile) {
        studentFacade.getCurrentStudent()
                .updateStudent(
                        s3Util.getFileUrl(s3Util.upload(profile)),
                        nickname
                );
    }
}

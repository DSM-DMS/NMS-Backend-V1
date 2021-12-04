package com.dsm.nms.domain.teacher.service;

import com.dsm.nms.domain.auth.facade.AuthCodeFacade;
import com.dsm.nms.domain.teacher.api.dto.request.LoginRequest;
import com.dsm.nms.domain.teacher.api.dto.request.SignUpRequest;
import com.dsm.nms.domain.teacher.entity.Teacher;
import com.dsm.nms.domain.teacher.facade.TeacherFacade;
import com.dsm.nms.domain.teacher.repository.TeacherRepository;
import com.dsm.nms.global.exception.InvalidPasswordException;
import com.dsm.nms.global.security.jwt.JwtTokenProvider;
import com.dsm.nms.global.security.jwt.dto.response.TokenResponse;
import com.dsm.nms.global.utils.auth.user.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TeacherServiceImpl implements TeacherService{

    private final TeacherRepository teacherRepository;
    private final TeacherFacade teacherFacade;
    private final AuthCodeFacade authCodeFacade;
    private final UserUtil userUtil;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    private static final String role = "teacher";

    @Override
    @Transactional
    public void signUp(SignUpRequest signUpRequest) {

        teacherFacade.existsUsernameFilter(signUpRequest.getUsername());
        userUtil.existEmailFilter(signUpRequest.getEmail());
        authCodeFacade.isCertifiedFilter(signUpRequest.getEmail());

        signUpRequest.encodePassword(passwordEncoder.encode(signUpRequest.getPassword()));
        teacherRepository.save(new Teacher(signUpRequest));
    }

    @Override
    public TokenResponse login(LoginRequest loginRequest) {

        return Optional.of(teacherFacade.getById(loginRequest.getId()))
                .filter(
                        teacher -> passwordEncoder.matches(loginRequest.getPassword(), teacher.getPassword())
                )
                .map(
                        teacher -> tokenProvider.generateToken(loginRequest.getId(), role)
                )
                .orElseThrow(() -> InvalidPasswordException.EXCEPTION);
    }

    @Override
    public TokenResponse reissue(String refreshToken) {
        return userUtil.reissue(refreshToken, role);
    }
}

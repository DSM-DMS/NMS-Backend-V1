package com.dsm.nms.domain.student.service;

import com.dsm.nms.domain.auth.facade.AuthCodeFacade;
import com.dsm.nms.domain.image.entity.Image;
import com.dsm.nms.domain.star.entity.Star;
import com.dsm.nms.domain.star.repository.StarRepository;
import com.dsm.nms.domain.student.api.dto.response.MyPageResponse;
import com.dsm.nms.domain.student.entity.Student;
import com.dsm.nms.domain.student.exception.InvalidGradeException;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StarRepository starRepository;
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
        studentRepository.save(
            studentFacade.getCurrentStudent()
                    .updateStudent(
                            userUtil.modifyProfileImage(profile),
                            nickname
                    )
        );
}

    @Override
    @Transactional(readOnly = true)
    public MyPageResponse getStudentMyPage() {
        Student student = studentFacade.getCurrentStudent();

        List<MyPageResponse.notice> staredNotice = starRepository.findByStudent(student)
                .stream()
                .map(Star::getNotice)
                .map(notice -> {
                    MyPageResponse.notice myNotice = MyPageResponse.notice.builder()
                            .id(notice.getId())
                            .title(notice.getTitle())
                            .writer(notice.getTeacher().getName())
                            .department(notice.getTeacher().getDepartment().toString())
                            .createdDate(notice.getCreatedDate())
                            .build();
                    return imageFilter(myNotice, notice.getImages());
                })
                .collect(Collectors.toList());

        return new MyPageResponse(student, staredNotice, changeGrade(student.getGrade().toString()));
    }

    private MyPageResponse.notice imageFilter(MyPageResponse.notice notice, List<Image> images) {
        if(!images.isEmpty())
            notice.inputImage(images.get(0).toString());

        return notice;
    }

    private String changeGrade(String grade) {
        switch (grade) {
            case "FIRST" : return "1";
            case "SECOND" : return "2";
            case "THIRD" : return "3";
            default: throw InvalidGradeException.EXCEPTION;
        }
    }
}

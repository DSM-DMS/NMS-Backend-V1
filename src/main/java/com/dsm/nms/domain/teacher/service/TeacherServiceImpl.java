package com.dsm.nms.domain.teacher.service;

import com.dsm.nms.domain.auth.facade.AuthCodeFacade;
import com.dsm.nms.domain.image.facade.ImageFacade;
import com.dsm.nms.domain.notice.facade.NoticeFacade;
import com.dsm.nms.domain.notice.repository.NoticeRepository;
import com.dsm.nms.domain.teacher.api.dto.request.LoginRequest;
import com.dsm.nms.domain.teacher.api.dto.request.SignUpRequest;
import com.dsm.nms.domain.teacher.api.dto.request.TeacherInfoRequest;
import com.dsm.nms.domain.teacher.api.dto.response.MyPageResponse;
import com.dsm.nms.domain.teacher.api.dto.response.ProfileResponse;
import com.dsm.nms.domain.teacher.entity.Teacher;
import com.dsm.nms.domain.teacher.facade.TeacherFacade;
import com.dsm.nms.domain.teacher.repository.TeacherRepository;
import com.dsm.nms.global.exception.InvalidPasswordException;
import com.dsm.nms.global.security.jwt.JwtTokenProvider;
import com.dsm.nms.global.security.jwt.dto.response.TokenResponse;
import com.dsm.nms.global.utils.auth.user.UserUtil;
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
public class TeacherServiceImpl implements TeacherService{

    private final S3Util s3Util;
    private final UserUtil userUtil;
    private final ImageFacade imageFacade;
    private final NoticeFacade noticeFacade;
    private final TeacherFacade teacherFacade;
    private final AuthCodeFacade authCodeFacade;
    private final JwtTokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final NoticeRepository noticeRepository;
    private final TeacherRepository teacherRepository;

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

        return Optional.of(teacherFacade.getByUsernameOrEmail(loginRequest.getId()))
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

    @Override
    @Transactional(readOnly = true)
    public ProfileResponse getTeacherProfile(Integer teacherId) {
        Teacher teacher = teacherFacade.getById(teacherId);

        return new ProfileResponse(teacher);
    }

    @Override
    @Transactional(readOnly = true)
    public MyPageResponse getTeacherMyPage() {
        Teacher teacher = teacherFacade.getCurrentTeacher();

        Integer noticeCount = noticeRepository.findByTeacher(teacher).size();
        List<MyPageResponse.notice> notices = noticeRepository.findByTeacher(teacher).stream()
                .map(notice -> MyPageResponse.notice.builder()
                        .id(notice.getId())
                        .writer(MyPageResponse.writer.builder()
                                .name(notice.getTeacher().getName())
                                .profileUrl(notice.getTeacher().getProfileUrl())
                                .build())
                        .createdDate(notice.getCreatedDate())
                        .updatedDate(notice.getUpdatedDate())
                        .targetTags(noticeFacade.getTargetTags(notice))
                        .title(notice.getTitle())
                        .content(notice.getContent())
                        .images(imageFacade.getNoticeImages(notice))
                        .starCount(notice.getStarCount())
                        .build())
                .collect(Collectors.toList());

        return new MyPageResponse(teacher, noticeCount, notices);
    }

    @Override
    @Transactional
    public void modifyTeacherInfo(TeacherInfoRequest teacherInfoRequest, MultipartFile profile) {

        teacherRepository.save(
                teacherFacade.getCurrentTeacher()
                        .updateInfo(
                                teacherInfoRequest,
                                userUtil.modifyProfileImage(profile)
                        )
        );
    }

}

package com.dsm.nms.global.utils.auth.user;

import com.dsm.nms.domain.refreshtoken.repository.RefreshTokenRepository;
import com.dsm.nms.domain.student.repository.StudentRepository;
import com.dsm.nms.domain.teacher.repository.TeacherRepository;
import com.dsm.nms.global.exception.EmailAlreadyExistsException;
import com.dsm.nms.global.exception.InvalidTokenException;
import com.dsm.nms.global.security.jwt.JwtTokenProvider;
import com.dsm.nms.global.security.jwt.dto.response.TokenResponse;
import com.dsm.nms.global.utils.aws.s3.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Component
public class UserUtil {

    private final S3Util s3Util;
    private final JwtTokenProvider tokenProvider;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.refreshExp}")
    private Long ttl;

    public void existEmailFilter(String email) {
        if(teacherRepository.findByEmail(email).isPresent() || studentRepository.findByEmail(email).isPresent())
            throw EmailAlreadyExistsException.EXCEPTION;
    }

    public Object getPrincipal() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Transactional
    public TokenResponse reissue(String refreshToken, String role) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .filter(token -> tokenProvider.isRefresh(token.getRefreshToken()))
                .filter(token -> tokenProvider.checkRole(token.getRefreshToken(), role))
                .map(token -> {
                    String id = token.getId();
                    return new TokenResponse(
                            tokenProvider.generateAccessToken(id, role),
                            token.reissue(tokenProvider.generateRefreshToken(id, role), ttl)
                    );
                })
                .orElseThrow(()-> InvalidTokenException.EXCEPTION);
    }

    public String modifyProfileImage(MultipartFile profile) {
        if(profile.isEmpty()) {
            return null;
        }

        return s3Util.getFileUrl(s3Util.upload(profile));
    }

}

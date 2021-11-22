package com.dsm.nms.global.security.jwt;

import com.dsm.nms.domain.refreshtoken.entity.RefreshToken;
import com.dsm.nms.domain.refreshtoken.repository.RefreshTokenRepository;
import com.dsm.nms.global.exception.ExpiredTokenException;
import com.dsm.nms.global.exception.InvalidRoleException;
import com.dsm.nms.global.exception.InvalidTokenException;
import com.dsm.nms.global.security.auth.StudentDetailsService;
import com.dsm.nms.global.security.auth.TeacherDetailsService;
import com.dsm.nms.global.utils.auth.dto.response.TokenResponse;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.util.annotation.Nullable;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final StudentDetailsService studentDetailsService;
    private final TeacherDetailsService teacherDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenResponse generateToken(String email, String role) {
        String access = generateAccessToken(email, role);
        String refresh = generateRefreshToken(email, role);

        refreshTokenRepository.save(
                RefreshToken.builder()
                        .email(email)
                        .refreshToken(refresh)
                        .refreshExpiration(jwtProperties.getRefreshExp() * 1000)
                        .build()
        );

        return new TokenResponse(access, refresh);
    }

    public String generateAccessToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("type", "access")
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .setExpiration(
                        new Date(System.currentTimeMillis() + jwtProperties.getAccessExp() * 1000)
                )
                .setIssuedAt(new Date())
                .compact();
    }

    public String generateRefreshToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("type", "refresh")
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .setExpiration(
                        new Date(System.currentTimeMillis() + jwtProperties.getRefreshExp() * 1000)
                )
                .setIssuedAt(new Date())
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(jwtProperties.getHeader());

        if(bearerToken != null && bearerToken.startsWith(jwtProperties.getPrefix())) {
            return bearerToken.substring(jwtProperties.getHeader().length() + 1);
        }
        return null;
    }

    public Authentication getAuthentication(String token) {
        Claims body = getBody(token);

        if(body.getExpiration().before(new Date())) {
            throw ExpiredTokenException.EXCEPTION;
        }

        UserDetails userDetails = getDetails(body);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private Claims getBody(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (MalformedJwtException | SignatureException e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    @Nullable
    private UserDetails getDetails(Claims body) {
        switch (body.get("role", String.class)) {
            case "teacher":
                return teacherDetailsService.loadUserByUsername(body.getSubject());
            case "student":
                return studentDetailsService.loadUserByUsername(body.getSubject());
            default: throw InvalidRoleException.EXCEPTION;
        }
    }

}

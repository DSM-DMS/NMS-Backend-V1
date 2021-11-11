package com.dsm.nms.global.security.jwt;

import com.dsm.nms.global.security.auth.StudentDetailsService;
import com.dsm.nms.global.security.auth.TeacherDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final StudentDetailsService studentDetailsService;
    private final TeacherDetailsService teacherDetailsService;

    public String generateAccessToken(String id, String role) {
        return Jwts.builder()
                .setSubject(id)
                .claim("type", "access")
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .setExpiration(
                        new Date(System.currentTimeMillis() + jwtProperties.getAccessExp() * 1000)
                )
                .setIssuedAt(new Date())
                .compact();
    }

    public String generateRefreshToken(String id, String role) {
        return Jwts.builder()
                .setSubject(id)
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
        if(bearerToken != null && bearerToken.startsWith(jwtProperties.getPrefix()))
            return bearerToken.substring(jwtProperties.getHeader().length()+1);
        return null;
    }

    public Authentication getAuthentication(String token) {
        Claims body = getBody(token);
        if(body.getExpiration().before(new Date()))
            return null;

        UserDetails userDetails = getDetails(body);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public Claims getBody(String token) {
            return Jwts.parser().setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token).getBody();
            //여기 예외점요
    }

    public UserDetails getDetails(Claims body) {
        if(body.get("role").equals("teacher"))
            return teacherDetailsService
                    .loadUserByUsername(body.getSubject());
        return studentDetailsService
                .loadUserByUsername(body.getSubject());
    }
}

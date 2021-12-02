package com.dsm.nms.domain.teacher.entity;

import com.dsm.nms.domain.teacher.api.dto.request.SignUpRequest;
import com.dsm.nms.global.entity.Writer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Teacher extends Writer {

    @Id
    @Column(nullable = false, unique = true)
    protected String email;

    @Column(nullable = false, columnDefinition = "char(60)")
    private String password;

    @Column(nullable = false, unique = true)
    private String username;

    private String phoneNumber;

    private String introduction;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Department department;

    public Teacher(SignUpRequest signUpRequest) {
        this.name = signUpRequest.getName();
        this.username = signUpRequest.getUsername();
        this.password = signUpRequest.getPassword();
        this.email = signUpRequest.getEmail();
        this.department = Department.valueOf(signUpRequest.getDepartment());
    }

    public Teacher updatePassword(String newPassword) {
        this.password = newPassword;
        return this;
    }

}
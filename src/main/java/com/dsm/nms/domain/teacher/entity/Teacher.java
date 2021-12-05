package com.dsm.nms.domain.teacher.entity;

import com.dsm.nms.domain.teacher.api.dto.request.SignUpRequest;
import com.dsm.nms.domain.teacher.api.dto.request.TeacherInfoRequest;
import com.dsm.nms.global.entity.Writer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue(value = "T")
@Entity
public class Teacher extends Writer {

    @Column(nullable = false)
    protected String email;

    @Column(nullable = false, columnDefinition = "char(60)")
    private String password;

    @Column(nullable = false, unique = true)
    private String username;

    @Size(min = 11, max = 11)
    private String phoneNumber;

    @Column(columnDefinition = "char(32)")
    private String introduce;

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

    public Teacher updateInfo(TeacherInfoRequest teacherInfoRequest, String profileUrl) {
        this.phoneNumber = teacherInfoRequest.getPhoneNumber();
        this.department = Department.valueOf(teacherInfoRequest.getDepartment());
        this.introduce = teacherInfoRequest.getIntroduce();
        this.profileUrl = profileUrl;
        return this;
    }

}
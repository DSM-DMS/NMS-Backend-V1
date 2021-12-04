package com.dsm.nms.domain.student.entity;

import com.dsm.nms.domain.star.entity.Star;
import com.dsm.nms.domain.student.api.dto.request.SignUpRequest;
import com.dsm.nms.global.entity.Writer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue(value = "S")
@Entity
public class Student extends Writer {

    @Column(nullable = false)
    protected String email;

    @Column(nullable = false, columnDefinition = "char(60)")
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Column(nullable = false, columnDefinition = "char(1)")
    private String classNum;

    @Column(nullable = false, columnDefinition = "char(2)")
    private String number;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<Star> stars;

    public Student(SignUpRequest signUpRequest) {
        this.nickname = signUpRequest.getNickname();
        this.grade = Grade.valueOf(signUpRequest.getGrade());
        this.name = signUpRequest.getName();
        this.classNum = signUpRequest.getClassNum();
        this.number = signUpRequest.getNumber();
        this.password = signUpRequest.getPassword();
        this.email = signUpRequest.getEmail();
    }

    public Student updatePassword(String newPassword) {
        this.password = newPassword;
        return this;
    }

    public Student updateStudent(String profileUrl, String nickname) {
        this.profileUrl = profileUrl;
        this.nickname = nickname;
        return this;
    }

}

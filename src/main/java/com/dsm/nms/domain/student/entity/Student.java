package com.dsm.nms.domain.student.entity;

import com.dsm.nms.domain.star.entity.Star;
import com.dsm.nms.global.utils.auth.dto.request.StudentSignUpRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "char(5)")
    private String name;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, columnDefinition = "char(60)")
    private String password;

    private String profileUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Column(nullable = false, columnDefinition = "char(1)")
    private String classNum;

    @Column(nullable = false, columnDefinition = "char(2)")
    private String number;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<Star> stars;

    public Student(StudentSignUpRequest studentSignUpRequest) {
        this.nickname = studentSignUpRequest.getNickname();
        this.grade = Grade.valueOf(studentSignUpRequest.getGrade());
        this.name = studentSignUpRequest.getName();
        this.classNum = studentSignUpRequest.getClassNum();
        this.number = studentSignUpRequest.getNumber();
        this.password = studentSignUpRequest.getPassword();
        this.email = studentSignUpRequest.getEmail();
    }

    public Student updatePassword(String newPassword) {
        this.password = newPassword;
        return this;
    }

}

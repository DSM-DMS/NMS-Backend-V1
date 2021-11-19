package com.dsm.nms.domain.teacher.entity;

import com.dsm.nms.domain.notice.entity.Notice;
import com.dsm.nms.domain.teacher.api.dto.request.SignUpRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Teacher {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;
    private String profileUrl;

    @Column(nullable = false)
    private String phoneNumber;
    private String introduction;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Department department;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.REMOVE)
    private List<Notice> notices;

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
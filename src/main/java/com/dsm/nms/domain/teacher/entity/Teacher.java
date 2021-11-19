package com.dsm.nms.domain.teacher.entity;

import com.dsm.nms.domain.notice.entity.Notice;
import lombok.AccessLevel;
import lombok.Builder;
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

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
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

    @Builder
    public Teacher(String name, String username, String email, String password, Department department) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.department = department;
    }

    public Teacher updatePassword(String newPassword) {
        this.password = newPassword;
        return this;
    }

}
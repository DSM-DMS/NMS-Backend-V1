package com.dsm.nms.entity.teacher;

import com.dsm.nms.entity.notice.Notice;
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

    private String name;
    private String username;
    private String password;
    private String email;
    private String profileUrl;

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

}
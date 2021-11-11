package com.dsm.nms.domain.student;

import com.dsm.nms.domain.star.Star;
import lombok.AccessLevel;
import lombok.Builder;
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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String gcn;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
    private String profileUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<Star> stars;

    @Builder
    public Student(String name, String gcn, String email, String password, Grade grade) {
        this.name = name;
        this.gcn = gcn;
        this.email = email;
        this.password = password;
        this.grade = grade;
    }

}

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

    @Builder
    public Student(String nickname, String name, Grade grade, String classNum, String number, String password, String email) {
        this.nickname = nickname;
        this.grade = grade;
        this.name = name;
        this.classNum = classNum;
        this.number = number;
        this.password = password;
        this.email = email;
    }

}

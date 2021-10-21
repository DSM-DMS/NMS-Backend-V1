package com.dsm.nms.entity.student;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String number;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    private String profileUrl;

    @Builder
    public Student(String name, String number, String email, String password, Grade grade) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.password = password;
        this.grade = grade;
    }

}

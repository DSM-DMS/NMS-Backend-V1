package com.dsm.nms.entity.comment;

import com.dsm.nms.entity.BaseTimeEntity;
import com.dsm.nms.entity.notice.Notice;
import com.dsm.nms.entity.student.Student;
import com.dsm.nms.entity.teacher.Teacher;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String comment;

    @JoinColumn(name = "notice_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Notice notice;

    @JoinColumn(name = "student_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    @JoinColumn(name = "teacher_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;

}

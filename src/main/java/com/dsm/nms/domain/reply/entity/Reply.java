package com.dsm.nms.domain.reply.entity;

import com.dsm.nms.domain.comment.entity.Comment;
import com.dsm.nms.domain.student.entity.Student;
import com.dsm.nms.domain.teacher.entity.Teacher;
import com.dsm.nms.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Reply extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String content;

    private boolean isTeacher;

    @JoinColumn(name = "comment_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Comment comment;

    @JoinColumn(name = "teacher_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;

    @JoinColumn(name = "student_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    public Reply(Comment comment, String content, Teacher teacher) {
        this.content = content;
        this.comment = comment;
        this.teacher = teacher;
    }

}

package com.dsm.nms.domain.comment.entity;

import com.dsm.nms.domain.BaseTimeEntity;
import com.dsm.nms.domain.notice.entity.Notice;
import com.dsm.nms.domain.reply.entity.Reply;
import com.dsm.nms.domain.student.entity.Student;
import com.dsm.nms.domain.teacher.entity.Teacher;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String content;

    @JoinColumn(name = "notice_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Notice notice;

    @JoinColumn(name = "student_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    @JoinColumn(name = "teacher_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
    private List<Reply> replies;

}

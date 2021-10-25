package com.dsm.nms.entity.star;

import com.dsm.nms.entity.BaseTimeEntity;
import com.dsm.nms.entity.notice.Notice;
import com.dsm.nms.entity.student.Student;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Star extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "notice_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Notice notice;

    @JoinColumn(name = "student_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    @Builder
    public Star(Notice notice, Student student) {
        this.notice = notice;
        this.student = student;
    }

}

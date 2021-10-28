package com.dsm.nms.entity.notice;

import com.dsm.nms.entity.BaseTimeEntity;
import com.dsm.nms.entity.image.Image;
import com.dsm.nms.entity.notice.noticetarget.NoticeTarget;
import com.dsm.nms.entity.star.Star;
import com.dsm.nms.entity.teacher.Teacher;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Notice extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @JoinColumn(name = "teacher_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;

    @OneToMany(mappedBy = "notice", cascade = CascadeType.REMOVE)
    private List<NoticeTarget> targets;

    @OneToMany(mappedBy = "notice", cascade = CascadeType.REMOVE)
    private List<Image> images;

    @OneToMany(mappedBy = "notice", cascade = CascadeType.REMOVE)
    private Set<Star> stars = new HashSet<>();

    @Builder
    public Notice(String title, String content, Teacher teacher) {
        this.title = title;
        this.content = content;
        this.teacher = teacher;
    }

}

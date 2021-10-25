package com.dsm.nms.entity.notice;

import com.dsm.nms.entity.BaseTimeEntity;
import com.dsm.nms.entity.image.Image;
import com.dsm.nms.entity.notice.target.Target;
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

    @OneToMany(mappedBy = "notice", cascade = CascadeType.REMOVE)
    private List<Target> targets;

    @OneToMany(mappedBy = "notice", cascade = CascadeType.REMOVE)
    private List<Image> images;

    @OneToMany(mappedBy = "notice", cascade = CascadeType.REMOVE)
    private Set<Star> stars = new HashSet<>();

    @JoinColumn(name = "teacher_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;

    @Builder
    public Notice(String title, String content, List<Target> targets, List<Image> images, Teacher teacher) {
        this.title = title;
        this.content = content;
        this.targets = targets;
        this.images = images;
        this.teacher = teacher;
    }

}

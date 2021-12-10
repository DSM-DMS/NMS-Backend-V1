package com.dsm.nms.domain.notice.entity;

import com.dsm.nms.global.entity.BaseTimeEntity;
import com.dsm.nms.domain.comment.entity.Comment;
import com.dsm.nms.domain.image.entity.Image;
import com.dsm.nms.domain.notice.api.dto.request.ModifyNoticeRequest;
import com.dsm.nms.domain.notice.entity.noticetarget.NoticeTarget;
import com.dsm.nms.domain.star.entity.Star;
import com.dsm.nms.domain.notice.api.dto.request.RegisterNoticeRequest;
import com.dsm.nms.domain.teacher.entity.Teacher;
import lombok.AccessLevel;
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

    private Integer starCount;

    @JoinColumn(name = "teacher_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;

    @OneToMany(mappedBy = "notice", cascade = CascadeType.REMOVE)
    private List<NoticeTarget> targets;

    @OneToMany(mappedBy = "notice", cascade = CascadeType.REMOVE)
    private List<Image> images;

    @OneToMany(mappedBy = "notice", cascade = CascadeType.REMOVE)
    private Set<Star> stars = new HashSet<>();

    @OneToMany(mappedBy = "notice", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    public Notice(RegisterNoticeRequest request, Teacher teacher) {
        this.title = request.getTitle();
        this.content = request.getContent();
        this.teacher = teacher;
        this.starCount = 0;
    }

    public Notice updateTitleAndContent(ModifyNoticeRequest noticeRequest) {
        this.title = noticeRequest.getTitle();
        this.content = noticeRequest.getContent();
        return this;
    }

    public void addStar() {
        this.starCount++;
    }

    public void cancelStar() {
        this.starCount--;
    }

}

package com.dsm.nms.domain.image.entity;

import com.dsm.nms.domain.BaseTimeEntity;
import com.dsm.nms.domain.notice.entity.Notice;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Image extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sequence", columnDefinition = "integer default 1")
    private Integer order;
    private String imagePath;
    private String imageUrl;

    @JoinColumn(name = "notice_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Notice notice;

    @Builder
    public Image(String imagePath, String imageUrl, Notice notice) {
        this.imagePath = imagePath;
        this.imageUrl = imageUrl;
        this.notice = notice;
    }

}

package com.dsm.nms.domain.reply.entity;

import com.dsm.nms.domain.comment.entity.Comment;
import com.dsm.nms.global.entity.BaseTimeEntity;
import com.dsm.nms.global.entity.Writer;
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

    @JoinColumn(name = "comment_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Comment comment;

    @JoinColumn(name = "writer_id")
    @ManyToOne
    private Writer writer;

    public Reply(Comment comment, String content, Writer writer) {
        this.content = content;
        this.comment = comment;
        this.writer = writer;
    }

}

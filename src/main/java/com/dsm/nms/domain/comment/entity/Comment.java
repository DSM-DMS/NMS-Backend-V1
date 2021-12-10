package com.dsm.nms.domain.comment.entity;

import com.dsm.nms.domain.notice.entity.Notice;
import com.dsm.nms.domain.reply.entity.Reply;
import com.dsm.nms.global.entity.BaseTimeEntity;
import com.dsm.nms.global.entity.Writer;
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

    @JoinColumn(name = "writer_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Writer writer;

    @OneToMany(mappedBy = "comment",
            fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE)
    private List<Reply> replies;

    public Comment(Notice notice, String content, Writer writer) {
        this.notice = notice;
        this.content = content;
        this.writer = writer;
    }
}

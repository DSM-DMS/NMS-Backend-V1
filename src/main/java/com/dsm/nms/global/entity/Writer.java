package com.dsm.nms.global.entity;

import com.dsm.nms.domain.comment.entity.Comment;
import com.dsm.nms.domain.reply.entity.Reply;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
@Entity
public class Writer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(nullable = false, columnDefinition = "char(5)")
    protected String name;

    protected String profileUrl;

    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY)
    private List<Reply> replies;

    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY)
    private List<Comment> comments;

}

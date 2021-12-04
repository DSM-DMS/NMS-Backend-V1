package com.dsm.nms.global.entity;

import com.dsm.nms.domain.reply.entity.Reply;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
@Entity
public class Writer {

    @Id @GeneratedValue
    protected Integer id;

    @Column(nullable = false, columnDefinition = "char(5)")
    protected String name;

    protected String profileUrl;

    @OneToOne(fetch = FetchType.LAZY)
    private Reply reply;

    @OneToOne(fetch = FetchType.LAZY)
    private Writer writer;

}

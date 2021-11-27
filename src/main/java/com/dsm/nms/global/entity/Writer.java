package com.dsm.nms.global.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
public abstract class Writer {

    @Column(nullable = false, columnDefinition = "char(5)")
    protected String name;

    protected String profileUrl;

    @Column(nullable = false, unique = true)
    protected String email;

    @Column(nullable = false, columnDefinition = "char(60)")
    protected String password;

}

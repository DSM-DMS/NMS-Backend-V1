package com.dsm.nms.global.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Writer {

    @Id @GeneratedValue
    protected Integer id;

    @Column(nullable = false, columnDefinition = "char(5)")
    protected String name;

    protected String profileUrl;

}

package com.dsm.nms.global.entity;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Writer {

    private String name;

    private String profileUrl;

}

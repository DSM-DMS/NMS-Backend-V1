package com.dsm.nms.global.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter @Setter
@Embeddable
public class Writer {

    private String name;

    private String profileUrl;

}

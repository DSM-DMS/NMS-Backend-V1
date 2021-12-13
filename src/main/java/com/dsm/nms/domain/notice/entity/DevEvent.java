package com.dsm.nms.domain.notice.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "dev_event_details")
public class DevEvent {

    @Id
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "event_day")
    private String eventDay;

    @Column(name = "host")
    private String host;

    @Column(name = "link")
    private String link;

    @Column(name = "created_date")
    private Timestamp createdDate;
}

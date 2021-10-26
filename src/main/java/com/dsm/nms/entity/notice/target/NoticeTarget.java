package com.dsm.nms.entity.notice.target;

import com.dsm.nms.entity.notice.Notice;
import lombok.*;
import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(NoticeTargetId.class)
@Entity
public class NoticeTarget {

    @Id
    @JoinColumn(name = "target_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Target target;

    @Id
    @JoinColumn(name = "notice_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Notice notice;

}

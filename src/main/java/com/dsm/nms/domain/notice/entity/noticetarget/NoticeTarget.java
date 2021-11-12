package com.dsm.nms.domain.notice.entity.noticetarget;

import com.dsm.nms.domain.notice.entity.Notice;
import com.dsm.nms.domain.notice.entity.target.Target;
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

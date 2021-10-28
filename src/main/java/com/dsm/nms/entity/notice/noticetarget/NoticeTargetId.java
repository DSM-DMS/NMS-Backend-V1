package com.dsm.nms.entity.notice.noticetarget;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeTargetId implements Serializable {
    private Integer target;
    private Integer notice;
}

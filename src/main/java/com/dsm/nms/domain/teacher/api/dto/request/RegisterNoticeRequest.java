package com.dsm.nms.domain.teacher.api.dto.request;

import com.dsm.nms.domain.notice.entity.target.TargetTag;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RegisterNoticeRequest {
    private String title;
    private String content;
    private List<TargetTag> tags;
}

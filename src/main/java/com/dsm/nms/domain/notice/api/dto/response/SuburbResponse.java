package com.dsm.nms.domain.notice.api.dto.response;

import com.dsm.nms.domain.notice.entity.DevEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SuburbResponse {
    private final Integer noticeCount;
    private final List<DevEvent> notices;
}

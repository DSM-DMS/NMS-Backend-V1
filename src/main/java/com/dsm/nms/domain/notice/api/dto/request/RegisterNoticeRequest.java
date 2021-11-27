package com.dsm.nms.domain.notice.api.dto.request;

import com.dsm.nms.domain.notice.entity.target.TargetTag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class RegisterNoticeRequest {

    @NotNull
    @Length(max = 30, message = "제목은 최대 30글자입니다.")
    private String title;

    @NotNull
    @Length(max = 500, message = "내용은 최대 500글자입니다.")
    private String content;

    @NotNull
    private List<TargetTag> tags;

}


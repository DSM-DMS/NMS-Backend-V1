package com.dsm.nms.domain.notice.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ModifyNoticeRequest {

    @NotNull
    @Length(max = 30, message = "제목은 최대 30글자입니다.")
    private String title;

    @NotNull
    @Length(max = 500, message = "내용은 최대 500글자입니다.")
    private String content;

}

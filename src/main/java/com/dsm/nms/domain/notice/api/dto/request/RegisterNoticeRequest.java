package com.dsm.nms.domain.notice.api.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class RegisterNoticeRequest {

    @NotNull(message = "title은 Null을 허용하지 않습니다.")
    @Length(max = 30, message = "제목은 최대 30글자입니다.")
    private String title;

    @NotNull(message = "content는 Null을 허용하지 않습니다.")
    @Length(max = 500, message = "내용은 최대 500글자입니다.")
    private String content;

    @NotNull(message = "tags는 Null을 허용하지 않습니다.")
    private List<String> tags;

}


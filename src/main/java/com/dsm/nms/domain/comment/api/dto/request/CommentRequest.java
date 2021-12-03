package com.dsm.nms.domain.comment.api.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CommentRequest {

    @NotBlank(message = "content는 Null, 공백을 허용하지 않습니다.")
    @Length(max = 250, message = "대충 500자 이하여야함")
    String content;
}

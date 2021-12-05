package com.dsm.nms.domain.student.api.dto.request;

import com.dsm.nms.domain.student.entity.Grade;
import com.dsm.nms.global.aop.Enum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "nickname은 Null, 공백을 허용하지 않습니다.")
    @Pattern(regexp = "^[\\S]+$", message = "nickname은 띄어쓰기를 허용하지 않습니다.")
    @Length(max = 10, message = "nickname은 10글자 제한입니다.")
    private String nickname;

    @NotBlank(message = "name은 Null, 공백을 허용하지 않습니다.")
    @Pattern(regexp = "^[\\S]+$", message = "name은 띄어쓰기를 허용하지 않습니다.")
    @Length(max = 5, message = "name은 5글자 아래여야 합니다.")
    private String name;

    @NotNull(message = "grade는 Null을 허용하지 않습니다.")
    @Enum(enumClass = Grade.class, message = "존재하지 않는 학년입니다.")
    private String grade;

    @NotBlank(message = "classNum은 Null, 공백을 허용하지 않습니다.")
    @Pattern(regexp = "^[1-4]$", message = "classNum은 1,2,3,4만 허용됩니다.")
    @Length(max = 1, message = "classNum은 1자리만 허용됩니다.")
    private String classNum;

    @NotBlank(message = "number는 Null, 공백을 허용하지 않습니다.")
    @Length(max = 2, message = "number는 2자리 이하만 허용됩니다.")
    private String number;

    @Pattern(regexp="(?=.*[a-z])(?=.*[!#$%&'()*+,-./:;<=>?@＼^_`{|}~])(?=.*[0-9])(?=\\S+$).{8,32}$",
            message = "password는 띄어쓰기를 제외한 영문 소문자와 숫자, 특수문자가 적어도 1개 이상씩 포함된 8자이상 32글자 이하의 비밀번호여야 합니다.")
    @NotBlank(message = "password는 Null, 공백을 허용하지 않습니다.")
    private String password;

    @Pattern(regexp = "^.*@dsm\\.hs\\.kr$", message = "dsm 공식 이메일(구글) 이여야합니다.")
    @NotBlank(message = "email은 Null, 공백을 허용하지 않습니다.")
    @Email(message = "email 형식이 올바르지 않습니다.")
    private String email;

    public void encodePassword(String password) {
        this.password = password;
    }
}

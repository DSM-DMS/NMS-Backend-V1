package com.dsm.nms.domain.teacher.api.dto.request;

import com.dsm.nms.domain.teacher.entity.Department;
import com.dsm.nms.global.aop.Enum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class TeacherInfoRequest {

    @Nullable
    private String phoneNumber;

    @NotNull(message = "department는 Null을 허용하지 않습니다.")
    @Enum(enumClass = Department.class, message = "존재하지 않는 부서입니다.")
    private String department;

    @Nullable
    private String introduce;

}

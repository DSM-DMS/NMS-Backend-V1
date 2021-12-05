package com.dsm.nms.domain.teacher.api.dto.response;

import com.dsm.nms.domain.teacher.entity.Department;
import com.dsm.nms.domain.teacher.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfileResponse {

    private final String name;
    private final String email;
    private final Department department;
    private final String phoneNumber;
    private final String introduce;
    private final String profileUrl;

    public ProfileResponse(Teacher teacher) {
        this.name = teacher.getName();
        this.email = teacher.getEmail();
        this.department = teacher.getDepartment();
        this.phoneNumber = teacher.getPhoneNumber();
        this.introduce = teacher.getIntroduce();
        this.profileUrl = teacher.getProfileUrl();
    }

}

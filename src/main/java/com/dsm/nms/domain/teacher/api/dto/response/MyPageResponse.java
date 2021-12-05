package com.dsm.nms.domain.teacher.api.dto.response;

import com.dsm.nms.domain.notice.entity.target.TargetTag;
import com.dsm.nms.domain.teacher.entity.Department;
import com.dsm.nms.domain.teacher.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class MyPageResponse {

    private final String name;
    private final String email;
    private final String username;
    private final Department department;
    private final String phoneNumber;
    private final String introduce;
    private final String profileUrl;
    private final Integer noticeCount;
    private final List<notice> myNotices;

    public MyPageResponse(Teacher teacher, Integer noticeCount, List<notice> notices) {
        this.name = teacher.getName();
        this.email = teacher.getEmail();
        this.username = teacher.getUsername();
        this.department = teacher.getDepartment();
        this.phoneNumber = teacher.getPhoneNumber();
        this.introduce = teacher.getIntroduce();
        this.profileUrl = teacher.getProfileUrl();
        this.noticeCount = noticeCount;
        this.myNotices = notices;
    }

    @Getter
    @Builder
    public static class notice {
        private final Integer id;
        private final writer writer;
        private final LocalDateTime createdDate;
        private final LocalDateTime updatedDate;
        private final List<TargetTag> targetTags;
        private final String title;
        private final String content;
        private final List<String> images;
        private final Integer starCount;
    }

    @Getter
    @Builder
    public static class writer {
        private final String name;
        private final String profileUrl;
    }

}

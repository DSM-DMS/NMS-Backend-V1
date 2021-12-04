package com.dsm.nms.domain.student.api.dto.response;

import com.dsm.nms.domain.student.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class MyPageResponse {

    private final String nickname;
    private final String name;
    private final String gcn;
    private final String email;
    private final String profileUrl;
    private final List<notice> likedNotices;

    @Builder
    public static class notice {
        private final Integer id;
        private final String title;
        private final String writer;
        private final String department;
        private final LocalDateTime createdDate;
        private final String image;
    }

    public MyPageResponse(Student student, List<notice> likedNotices) {
        this.nickname = student.getNickname();
        this.name = student.getName();
        this.gcn = student.getGrade() + student.getClassNum() + student.getNumber();
        this.email = student.getEmail();
        this.profileUrl = student.getProfileUrl();
        this.likedNotices = likedNotices;
    }
}

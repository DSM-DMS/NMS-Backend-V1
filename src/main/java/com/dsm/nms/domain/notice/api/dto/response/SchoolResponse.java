package com.dsm.nms.domain.notice.api.dto.response;

import com.dsm.nms.domain.notice.entity.target.TargetTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class SchoolResponse {
    private final Integer noticeCount;
    private final List<notice> notices;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class notice {
        private final Integer noticeId;
        private final String title;
        private final String content;
        private final writer writer;
        private final List<TargetTag> targets;
        private final LocalDateTime createdDate;
        private final LocalDateTime updatedDate;
        private final List<String> images;
        private final boolean isStar;
        private final Integer starCount;
        private final Integer commentCount;
        private final List<comment> comments;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class writer {
        private final Integer id;
        private final String name;
        private final String profileUrl;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class comment {
        private final Integer id;
        private final writer writer;
        private final String content;
        private final LocalDateTime createdDate;
        private final Integer replyCount;
        private final List<reply> replies;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class reply {
        private final Integer id;
        private final writer writer;
        private final String content;
        private final LocalDateTime createdDate;
    }

}

package com.dsm.nms.domain.notice.facade;

import com.dsm.nms.domain.notice.entity.Notice;
import com.dsm.nms.domain.notice.entity.noticetarget.NoticeTarget;
import com.dsm.nms.domain.notice.entity.target.Target;
import com.dsm.nms.domain.notice.entity.target.TargetTag;
import com.dsm.nms.domain.notice.repository.NoticeTargetRepository;
import com.dsm.nms.domain.notice.repository.TargetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class NoticeFacade {

    private final TargetRepository targetRepository;
    private final NoticeTargetRepository noticeTargetRepository;

    public void addTargetField(Notice notice, List<TargetTag> tags) {
        tags.forEach(targetTag -> noticeTargetRepository.save(NoticeTarget.builder()
                .target(getTarget(targetTag))
                .notice(notice)
                .build()));
    }

    private Target getTarget(TargetTag tag) {
        return targetRepository.findByTargetTag(tag)
                .orElseThrow(() -> new RuntimeException("error"));
    }

}

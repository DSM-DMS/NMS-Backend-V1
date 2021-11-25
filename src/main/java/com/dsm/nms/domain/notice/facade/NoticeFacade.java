package com.dsm.nms.domain.notice.facade;

import com.dsm.nms.domain.notice.entity.Notice;
import com.dsm.nms.domain.notice.entity.noticetarget.NoticeTarget;
import com.dsm.nms.domain.notice.entity.target.Target;
import com.dsm.nms.domain.notice.entity.target.TargetTag;
import com.dsm.nms.domain.notice.exception.NoticeNotFoundException;
import com.dsm.nms.domain.notice.exception.TargetNotFoundException;
import com.dsm.nms.domain.notice.repository.NoticeRepository;
import com.dsm.nms.domain.notice.repository.NoticeTargetRepository;
import com.dsm.nms.domain.notice.repository.TargetRepository;
import com.dsm.nms.global.aop.Enum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class NoticeFacade {

    private final TargetRepository targetRepository;
    private final NoticeRepository noticeRepository;
    private final NoticeTargetRepository noticeTargetRepository;

    public void addTargetTags(Notice notice, List<TargetTag> tags) {
        tags.forEach(targetTag -> noticeTargetRepository.save(NoticeTarget.builder()
                .target(getTarget(targetTag))
                .notice(notice)
                .build()));
    }

    public Notice getByNoticeId(Integer noticeId) {
        return noticeRepository.findById(noticeId)
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);
    }

    private Target getTarget(TargetTag tag) {
        return targetRepository.findByTargetTag(tag)
                .or(() -> Optional.of(new Target(tag)))
                .orElseThrow(() -> TargetNotFoundException.EXCEPTION);
    }

}

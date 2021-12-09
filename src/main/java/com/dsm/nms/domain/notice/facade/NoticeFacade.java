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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Component
public class NoticeFacade {

    private final TargetRepository targetRepository;
    private final NoticeRepository noticeRepository;
    private final NoticeTargetRepository noticeTargetRepository;

    public void addTargetTags(Notice notice, List<String> tags) {
        tags.forEach(targetTag -> noticeTargetRepository.save(NoticeTarget.builder()
                .target(getTarget(TargetTag.valueOf(targetTag)))
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
                .map(targetRepository::save)
                .orElseThrow(() -> TargetNotFoundException.EXCEPTION);
    }

    public List<TargetTag> getTargetTags(Notice notice) {
        return noticeTargetRepository.findByNoticeId(notice.getId()).stream()
                .map(noticeTarget -> noticeTarget.getTarget().getTargetTag())
                .collect(toList());
    }

    public Integer getCounts(long count) {
        return Math.toIntExact(count);
    }

}

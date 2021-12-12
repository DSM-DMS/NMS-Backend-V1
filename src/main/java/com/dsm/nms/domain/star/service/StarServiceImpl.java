package com.dsm.nms.domain.star.service;

import com.dsm.nms.domain.notice.facade.NoticeFacade;
import com.dsm.nms.domain.star.entity.Star;
import com.dsm.nms.domain.star.exception.StarAlreadyExistsException;
import com.dsm.nms.domain.star.exception.StarNotFoundException;
import com.dsm.nms.domain.star.repository.StarRepository;
import com.dsm.nms.domain.student.facade.StudentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StarServiceImpl implements StarService{

    private final NoticeFacade noticeFacade;
    private final StudentFacade studentFacade;
    private final StarRepository starRepository;

    @Override
    @Transactional
    public void add(Integer noticeId) {

        if(starRepository.findByNoticeId(noticeId).isPresent())
            throw StarAlreadyExistsException.EXCEPTION;

        starRepository.save(Star.builder()
                .notice(noticeFacade.getByNoticeId(noticeId))
                .student(studentFacade.getCurrentStudent())
                .build());

        noticeFacade.getByNoticeId(noticeId).addStar();
    }

    @Override
    @Transactional
    public void cancel(Integer noticeId) {
        Star star = starRepository.findByNoticeId(noticeId)
                .orElseThrow(() -> StarNotFoundException.EXCEPTION);

        starRepository.delete(star);
        noticeFacade.getByNoticeId(noticeId).cancelStar();
    }

}

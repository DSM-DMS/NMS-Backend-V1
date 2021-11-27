package com.dsm.nms.domain.star.service;

import com.dsm.nms.domain.notice.facade.NoticeFacade;
import com.dsm.nms.domain.star.entity.Star;
import com.dsm.nms.domain.star.exception.StarNotFoundException;
import com.dsm.nms.domain.star.repository.StarRepository;
import com.dsm.nms.domain.student.facade.StudentFacade;
import com.dsm.nms.global.security.auth.StudentDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StarServiceImpl implements StarService{

    private final StarRepository starRepository;
    private final NoticeFacade noticeFacade;
    private final StudentFacade studentFacade;

    @Override
    public void star(int noticeId) {
        starRepository.save(Star.builder()
                .notice(noticeFacade.getByNoticeId(noticeId))
                .student(studentFacade.getCurrentStudent())
                .build());
    }

    @Override
    public void cancel(int commentId) {
        Star star = starRepository.findById(commentId)
                .orElseThrow(() -> StarNotFoundException.EXCEPTION);

        starRepository.delete(star);
    }
}

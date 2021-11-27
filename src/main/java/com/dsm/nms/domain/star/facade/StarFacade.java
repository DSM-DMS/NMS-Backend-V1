package com.dsm.nms.domain.star.facade;

import com.dsm.nms.domain.notice.entity.Notice;
import com.dsm.nms.domain.star.repository.StarRepository;
import com.dsm.nms.domain.student.facade.StudentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StarFacade {

    private final StudentFacade studentFacade;
    private final StarRepository starRepository;

    public boolean checkIsStar(Notice notice) {
        return starRepository.findByNoticeAndStudent(notice, studentFacade.getCurrentStudent()).isPresent();
    }

}

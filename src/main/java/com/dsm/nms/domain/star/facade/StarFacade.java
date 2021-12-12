package com.dsm.nms.domain.star.facade;

import com.dsm.nms.domain.notice.entity.Notice;
import com.dsm.nms.domain.star.repository.StarRepository;
import com.dsm.nms.domain.student.facade.StudentFacade;
import com.dsm.nms.global.security.auth.StudentDetails;
import com.dsm.nms.global.utils.auth.user.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StarFacade {

    private final UserUtil userUtil;
    private final StudentFacade studentFacade;
    private final StarRepository starRepository;

    public boolean checkIsStar(Notice notice) {
        Object principal = userUtil.getPrincipal();

        if(!(principal instanceof StudentDetails))
            return false;

        return starRepository.findByNoticeAndStudent(notice, studentFacade.getCurrentStudent()).isPresent();
    }

    public Integer getStarCount(Notice notice) {
        return notice.getStarCount();
    }

}

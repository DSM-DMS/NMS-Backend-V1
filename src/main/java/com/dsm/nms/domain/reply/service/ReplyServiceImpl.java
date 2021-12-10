package com.dsm.nms.domain.reply.service;

import com.dsm.nms.domain.reply.facade.ReplyFacade;
import com.dsm.nms.domain.student.facade.StudentFacade;
import com.dsm.nms.domain.teacher.facade.TeacherFacade;
import com.dsm.nms.global.exception.InvalidRoleException;
import com.dsm.nms.global.security.auth.StudentDetails;
import com.dsm.nms.global.security.auth.TeacherDetails;
import com.dsm.nms.global.utils.auth.user.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReplyServiceImpl implements ReplyService {

    private final ReplyFacade replyFacade;
    private final TeacherFacade teacherFacade;
    private final StudentFacade studentFacade;
    private final UserUtil userUtil;

    @Override
    @Transactional
    public void addReply(Integer commentId, String content) {
        Object principal = userUtil.getPrincipal();

        if (principal instanceof TeacherDetails) {
            replyFacade.createReply(commentId, content, teacherFacade.getCurrentTeacher());
        } else if (principal instanceof StudentDetails) {
            replyFacade.createReply(commentId, content, studentFacade.getCurrentStudent());
        } else throw InvalidRoleException.EXCEPTION;

    }

    @Override
    @Transactional
    public void removeReply(Integer replyId) {
        replyFacade.removeReply(replyId);
    }

}

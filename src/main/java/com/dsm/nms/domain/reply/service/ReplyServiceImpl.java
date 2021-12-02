package com.dsm.nms.domain.reply.service;

import com.dsm.nms.domain.reply.facade.ReplyFacade;
import com.dsm.nms.domain.student.entity.Student;
import com.dsm.nms.domain.student.facade.StudentFacade;
import com.dsm.nms.domain.teacher.entity.Teacher;
import com.dsm.nms.domain.teacher.facade.TeacherFacade;
import com.dsm.nms.global.exception.InvalidRoleException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReplyServiceImpl implements ReplyService {

    private final ReplyFacade replyFacade;
    private final TeacherFacade teacherFacade;
    private final StudentFacade studentFacade;

    @Override
    @Transactional
    public void addReply(Integer commentId, String content) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof Teacher) {
            replyFacade.createReply(commentId, content, teacherFacade.getCurrentTeacher());
        } else if (principal instanceof Student) {
            replyFacade.createReply(commentId, content, studentFacade.getCurrentStudent());
        } else throw InvalidRoleException.EXCEPTION;

    }

    @Override
    @Transactional
    public void removeReply(Integer replyId) {
        replyFacade.removeReply(replyId);
    }

}

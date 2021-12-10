package com.dsm.nms.domain.comment.service;

import com.dsm.nms.domain.comment.facade.CommentFacade;
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
public class CommentServiceImpl implements CommentService{

    private final CommentFacade commentFacade;
    private final TeacherFacade teacherFacade;
    private final StudentFacade studentFacade;
    private final UserUtil userUtil;

    @Override
    @Transactional
    public void addComment(Integer noticeId, String content) {

        Object principal = userUtil.getPrincipal();

        if (principal instanceof TeacherDetails) {
            commentFacade.createComment(noticeId, content, teacherFacade.getCurrentTeacher());
        } else if (principal instanceof StudentDetails) {
            commentFacade.createComment(noticeId, content, studentFacade.getCurrentStudent());
        } else throw InvalidRoleException.EXCEPTION;

    }

    @Override
    public void removeComment(Integer commentId) {
        commentFacade.removeComment(commentId);
    }
}

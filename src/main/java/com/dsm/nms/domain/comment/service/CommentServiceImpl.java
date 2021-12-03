package com.dsm.nms.domain.comment.service;

import com.dsm.nms.domain.comment.api.dto.request.CommentRequest;
import com.dsm.nms.domain.comment.entity.Comment;
import com.dsm.nms.domain.comment.exception.CommentNotFoundException;
import com.dsm.nms.domain.comment.facade.CommentFacade;
import com.dsm.nms.domain.comment.repository.CommentRepository;
import com.dsm.nms.domain.student.entity.Student;
import com.dsm.nms.domain.student.facade.StudentFacade;
import com.dsm.nms.domain.teacher.entity.Teacher;
import com.dsm.nms.domain.teacher.facade.TeacherFacade;
import com.dsm.nms.global.exception.InvalidRoleException;
import com.dsm.nms.global.utils.auth.user.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{

    private final CommentFacade commentFacade;
    private final TeacherFacade teacherFacade;
    private final StudentFacade studentFacade;
    private final UserUtil userUtil;

    @Override
    public void addComment(Integer noticeId, String content) {

        Object principal = userUtil.getPrincipal();

        if (principal instanceof Teacher) {
            commentFacade.createComment(noticeId, content, teacherFacade.getCurrentTeacher());
        } else if (principal instanceof Student) {
            commentFacade.createComment(noticeId, content, studentFacade.getCurrentStudent());
        } else throw InvalidRoleException.EXCEPTION;

    }

    @Override
    public void removeComment(Integer commentId) {
        commentFacade.removeComment(commentId);
    }
}

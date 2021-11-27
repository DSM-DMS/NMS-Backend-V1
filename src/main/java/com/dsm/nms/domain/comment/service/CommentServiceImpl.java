package com.dsm.nms.domain.comment.service;

import com.dsm.nms.domain.comment.api.dto.request.CommentRequest;
import com.dsm.nms.domain.comment.entity.Comment;
import com.dsm.nms.domain.comment.exception.CommentNotFoundException;
import com.dsm.nms.domain.comment.repository.CommentRepository;
import com.dsm.nms.domain.student.entity.Student;
import com.dsm.nms.domain.student.facade.StudentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final StudentFacade studentFacade;

    @Override
    public void comment(int noticeId, CommentRequest commentRequest) {

        Student student = studentFacade.getCurrentStudent();

        commentRepository.save(Comment.builder()
                .commentRequest(commentRequest)
                .name(student.getName())
                .profileUrl(student.getProfileUrl())
                .build());
    }

    @Override
    public void delComment(int commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> CommentNotFoundException.EXCEPTION);

        commentRepository.delete(comment);
    }
}

package com.dsm.nms.domain.reply.service;

import com.dsm.nms.domain.reply.facade.ReplyFacade;
import com.dsm.nms.domain.teacher.facade.TeacherFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReplyServiceImpl implements ReplyService {

    private final ReplyFacade replyFacade;
    private final TeacherFacade teacherFacade;

    @Override
    @Transactional
    public void addReply(Integer commentId, String content) {
        replyFacade.addReply(commentId, content, teacherFacade.getCurrentTeacher());
    }

}

package com.dsm.nms.domain.teacher.api;

import com.dsm.nms.domain.teacher.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TeacherController {

    private final TeacherService teacherService;

}

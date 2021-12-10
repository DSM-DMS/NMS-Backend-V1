package com.dsm.nms.domain.star.api;

import com.dsm.nms.domain.star.service.StarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/star")
public class StarController {

    private final StarService starService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void star(@RequestParam("notice-id") Integer noticeId) {
        starService.add(noticeId);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@RequestParam("notice-id") Integer commentId) {
        starService.cancel(commentId);
    }
}

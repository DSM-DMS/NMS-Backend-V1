package com.dsm.nms.domain.auth.api;

import com.dsm.nms.domain.auth.api.dto.request.SendCodeRequest;
import com.dsm.nms.domain.auth.api.dto.request.VerifyCodeRequest;
import com.dsm.nms.domain.auth.service.AuthCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/email")
public class AuthController {

    private final AuthCodeService authCodeService;

    @PostMapping("/")
    public void sendCode(@RequestBody @Valid SendCodeRequest sendCodeRequest) {
        authCodeService.sendCode(sendCodeRequest);
    }

    @PutMapping("/")
    public void verifyCode(@RequestBody @Valid VerifyCodeRequest verifyCodeRequest) {
        authCodeService.verifyCode(verifyCodeRequest);
    }
}

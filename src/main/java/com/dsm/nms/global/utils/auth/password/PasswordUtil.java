package com.dsm.nms.global.utils.auth.password;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PasswordUtil {

    public boolean checkPassword(String password, String reqPassword) {
        return password.equals(reqPassword);
    }

}

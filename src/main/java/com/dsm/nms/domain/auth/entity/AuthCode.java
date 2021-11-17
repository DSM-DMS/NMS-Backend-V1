package com.dsm.nms.domain.auth.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash
public class AuthCode {

    @Id
    private String email;

    private String code;

    private boolean isCertified;

    @TimeToLive
    private long ttl;

    public AuthCode(String email, String code) {
        this.email = email;
        this.code = code;
        this.isCertified = false;
        this.ttl = 30L;
    }

    public String updateAuthCode(String code) {
        this.code = code;
        this.ttl = 30L;
        return this.email;
    }

    public boolean isAuthCode(String code) {
        return this.code.equals(code);
    }

    public boolean getCertified() {
        return this.isCertified;
    }

    public AuthCode changeCertified() {
        this.isCertified = true;
        this.ttl = 300L;
        return this;
    }
}

package com.dsm.nms.domain.auth.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash
public class AuthCode {

    @Id
    private String email;

    private String code;

    private boolean certified;

    @TimeToLive
    private long ttl;

    public AuthCode(String email, String code) {
        this.email = email;
        this.code = code;
        this.certified = false;
        this.ttl = 30L;
    }

    public AuthCode updateAuthCode(String code) {
        this.code = code;
        this.ttl = 30L;
        return this;
    }

    public boolean isAuthCode(String code) {
        return this.code.equals(code);
    }

    public AuthCode changeCertified() {
        this.certified = true;
        this.ttl = 300L;
        return this;
    }
}

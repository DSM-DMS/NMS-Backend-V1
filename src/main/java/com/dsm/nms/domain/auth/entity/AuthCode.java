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
        this.ttl = 10L;
    }

    public String updateAuthCode(String code) {
        this.code = code;
        this.ttl = 10L;
        return this.email;
    }
}

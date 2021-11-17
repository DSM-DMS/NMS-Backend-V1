package com.dsm.nms.domain.auth.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash
public class AuthCodeLimit {

    @Id
    private String email;

    private int count;

    private long ttl;

    public AuthCodeLimit(String email) {
        this.email = email;
        this.count = 1;
        this.ttl = 180L;
    }

    public AuthCodeLimit plusCount() {
        this.count++;
        return this;
    }

    public int getCount() {
        return this.count;
    }
}

package com.dsm.nms.domain.refreshtoken.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash
public class RefreshToken implements Serializable {

    @Id
    private String email;

    @Indexed
    private String refreshToken;

    @TimeToLive
    private Long refreshExpiration;

    @Builder
    public RefreshToken(String email, String refreshToken, Long refreshExpiration) {
        this.email = email;
        this.refreshToken = refreshToken;
        this.refreshExpiration = refreshExpiration;
    }

}

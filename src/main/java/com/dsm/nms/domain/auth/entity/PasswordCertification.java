package com.dsm.nms.domain.auth.entity;

import com.dsm.nms.domain.student.entity.Student;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash
public class PasswordCertification {

    @Id
    private String email;

    private boolean certified;

    @TimeToLive
    private long ttl;

    public PasswordCertification(String email) {
        this.email = email;
        this.certified = true;
        this.ttl = 30L;
    }

}

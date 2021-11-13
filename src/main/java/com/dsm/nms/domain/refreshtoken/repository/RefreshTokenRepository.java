package com.dsm.nms.domain.refreshtoken.repository;

import com.dsm.nms.domain.refreshtoken.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}

package com.dsm.nms.domain.auth.repository;

import com.dsm.nms.domain.auth.entity.AuthCode;
import org.springframework.data.repository.CrudRepository;

public interface AuthCodeRepository extends CrudRepository<AuthCode, String> {
}

package com.dsm.nms.domain.auth.repository;

import com.dsm.nms.domain.auth.entity.AuthCodeLimit;
import org.springframework.data.repository.CrudRepository;

public interface AuthCodeLimitRepository extends CrudRepository<AuthCodeLimit, String> {
}

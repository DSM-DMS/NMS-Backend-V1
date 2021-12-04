package com.dsm.nms.domain.auth.repository;

import com.dsm.nms.domain.auth.entity.PasswordCertification;
import org.springframework.data.repository.CrudRepository;

public interface PasswordCertificationRepository extends CrudRepository<PasswordCertification, String> {
}

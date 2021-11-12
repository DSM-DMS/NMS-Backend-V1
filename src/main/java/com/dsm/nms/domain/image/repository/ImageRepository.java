package com.dsm.nms.domain.image.repository;

import com.dsm.nms.domain.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}

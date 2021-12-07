package com.dsm.nms.domain.image.repository;

import com.dsm.nms.domain.image.entity.Image;
import com.dsm.nms.domain.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    Optional<Image> findByImagePath(String imagePath);
    void deleteByNotice(Notice notice);
}

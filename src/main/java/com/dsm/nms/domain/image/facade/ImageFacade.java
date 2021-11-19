package com.dsm.nms.domain.image.facade;

import com.dsm.nms.domain.image.entity.Image;
import com.dsm.nms.domain.image.repository.ImageRepository;
import com.dsm.nms.domain.notice.entity.Notice;
import com.dsm.nms.global.utils.aws.s3.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ImageFacade {

    private final S3Util s3Util;
    private final ImageRepository imageRepository;

    public void addImages(Notice notice, List<MultipartFile> images) {
        for(MultipartFile image : images) {
            String fileName = s3Util.upload(image);

            imageRepository.save(Image.builder()
                    .imagePath(fileName)
                    .imageUrl(s3Util.getFileUrl(fileName))
                    .notice(notice)
                    .build());
        }
    }

}

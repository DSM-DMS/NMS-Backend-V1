package com.dsm.nms.global.utils.aws.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.dsm.nms.domain.image.exception.ImageNotFoundException;
import com.dsm.nms.domain.image.repository.ImageRepository;
import com.dsm.nms.global.config.S3Config;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class S3Util {

    private final S3Config s3Config;
    private final AmazonS3Client amazonS3Client;
    private final ImageRepository imageRepository;

    public String upload(MultipartFile image) {
        String fileName = "nms/" + UUID.randomUUID() + image.getOriginalFilename();

        try {
            amazonS3Client.putObject(new PutObjectRequest(s3Config.getBucket(), fileName, image.getInputStream(), null)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw ImageNotFoundException.EXCEPTION;
        }

        return fileName;
    }

    private void removeFile(String imagePath) {
        amazonS3Client.deleteObject(new DeleteObjectRequest(s3Config.getBucket(), imagePath));
    }

    public void removeFromS3() {
        amazonS3Client.listObjects(s3Config.getBucket()).getObjectSummaries().stream()
                .map(S3ObjectSummary::getKey)
                .filter(object -> imageRepository.findByImagePath(object).isEmpty())
                .forEach(this::removeFile);
    }

    public String getFileUrl(String fileName) {
        return amazonS3Client.getUrl(s3Config.getBucket(), fileName).toString();
    }

}

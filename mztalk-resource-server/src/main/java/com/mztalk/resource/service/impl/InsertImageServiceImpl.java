package com.mztalk.resource.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.mztalk.resource.domain.dto.ImagesDto;
import com.mztalk.resource.domain.entity.Images;
import com.mztalk.resource.repository.ImageRepository;
import com.mztalk.resource.service.InsertImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InsertImageServiceImpl implements InsertImageService {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final ImageRepository imageRepository;

    private final AmazonS3 amazonS3;

    @Override
    public void uploadImage(MultipartFile multipartFile, ImagesDto imagesDto) throws IOException {
        String s3FileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentLength(multipartFile.getInputStream().available());

        amazonS3.putObject(bucket, s3FileName, multipartFile.getInputStream(), objMeta);

        Images images = Images.builder()
                .imageUrl(amazonS3.getUrl(bucket, s3FileName).toString())
                .serviceName(imagesDto.getServiceName())
                .bNo(Long.parseLong(imagesDto.getBNo()))
                .imageLevel(Long.parseLong(imagesDto.getImageLevel()))
                .status("Y")
                .build();

        imageRepository.save(images);
    }
}

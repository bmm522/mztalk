package com.mztalk.resource.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.mztalk.resource.domain.dto.ImagesDto;
import com.mztalk.resource.domain.entity.Images;
import com.mztalk.resource.repository.ImageRepository;
import com.mztalk.resource.service.InsertImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class InsertImageServiceImpl implements InsertImageService {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final ImageRepository imageRepository;

    private final AmazonS3 amazonS3;


    @Override
    public int insertImage(MultipartFile multipartFile, ImagesDto imagesDto) throws IOException {
        Images images = null;

        try {
            images = getImages(multipartFile, imagesDto);
        } catch (Exception e){
            log.error("Fail Save Image");
            return 0;
        }
        imageRepository.save(images);
        return 1;
    }

    @Override
    public int insertImages(List<MultipartFile> multipartFileList, ImagesDto imagesDto) {

    }




    private Images getImages(MultipartFile multipartFile,  ImagesDto imagesDto) throws IOException {
        return Images.builder()
                .imageUrl(uploadImage(multipartFile, imagesDto))
                .serviceName(imagesDto.getServiceName())
                .bNo(Long.parseLong(imagesDto.getBNo()))
                .imageLevel(Long.parseLong(imagesDto.getImageLevel()))
                .status("Y")
                .build();
    }

    private String uploadImage(MultipartFile multipartFile, ImagesDto imagesDto) throws IOException {
        String s3FileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentLength(multipartFile.getInputStream().available());

        amazonS3.putObject(bucket, s3FileName, multipartFile.getInputStream(), objMeta);
        return amazonS3.getUrl(bucket, s3FileName).toString();
    }


}

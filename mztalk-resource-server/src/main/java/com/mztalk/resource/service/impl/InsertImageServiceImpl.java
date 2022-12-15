package com.mztalk.resource.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.mztalk.resource.domain.Role;
import com.mztalk.resource.domain.dto.ImagesDto;
import com.mztalk.resource.domain.entity.Images;
import com.mztalk.resource.repository.ImageRepository;
import com.mztalk.resource.service.InsertImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class InsertImageServiceImpl implements InsertImageService {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final ImageRepository imageRepository;

    private final AmazonS3 amazonS3;



    @Override
    public int insertImage(MultipartFile multipartFile, ImagesDto imagesDto){

       try{
           saveImages(multipartFile,imagesDto, Role.UPLOAD_SUB);
       }
       catch (IOException e){
           log.error("Fail Image Save");
           return 0;
       };
       return 1;
    }

    @Override
    public int insertImages(List<MultipartFile> multipartFileList, ImagesDto imagesDto) {

        for(int i = 0 ; i < multipartFileList.size() ; i++){

            if(i == 0){
                try {
                    saveImages(multipartFileList.get(i), imagesDto, Role.UPLOAD_MAIN);
                } catch (IOException e){
                    log.error("Fail Images Save");
                    return 0;
                }
            } else {
                try {
                    saveImages(multipartFileList.get(i),imagesDto,Role.UPLOAD_SUB);
                } catch (IOException e){
                    log.error("Fail Images Save");
                    return 0;
                }

            }
        }



        return 1;
    }

    @Override
    public int insertMainImage(MultipartFile multipartFile, ImagesDto imagesDto) {
        try{
            imageRepository.changeMainImageToSubImage(Long.parseLong(imagesDto.getBNo()), imagesDto.getServiceName());
            imageRepository.commit();
            saveImages(multipartFile,imagesDto, Role.UPLOAD_MAIN);
        }
        catch (IOException e){
            log.error("Fail Image Save");
            return 0;
        };
        return 1;
    }

    private void saveImages(MultipartFile multipartFile, ImagesDto imagesDto, Role role) throws IOException {
        String imageName = multipartFile.getOriginalFilename();
        Images images =null;

            switch (role){
                case UPLOAD_MAIN:
                    images = imagesDto.toImagesWhenMain(imageName, uploadImageToAwsS3(multipartFile));
                    break;
                case UPLOAD_SUB:
                    images = imagesDto.toImagesWhenSub(imageName,uploadImageToAwsS3(multipartFile));
                    break;
            }
            imageRepository.save(images);
    }

    private ConcurrentHashMap<String, String> uploadImageToAwsS3(MultipartFile multipartFile) throws IOException {

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

        String s3FileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentLength(multipartFile.getInputStream().available());

        amazonS3.putObject(bucket, s3FileName, multipartFile.getInputStream(), objMeta);
        map.put("key",amazonS3.getObject(bucket,s3FileName).getKey());
        map.put("url",amazonS3.getUrl(bucket, s3FileName).toString());

        return map;
    }


}



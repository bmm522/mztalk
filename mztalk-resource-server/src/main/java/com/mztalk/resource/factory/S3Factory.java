package com.mztalk.resource.factory;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class S3Factory {


    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;


    private final AmazonS3 amazonS3;
    public ConcurrentHashMap<String, String> uploadImageToAwsS3(MultipartFile multipartFile) throws IOException {

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

        String s3FileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentLength(multipartFile.getInputStream().available());

        amazonS3.putObject(bucket, s3FileName, multipartFile.getInputStream(), objMeta);
        map.put("key",amazonS3.getObject(bucket,s3FileName).getKey());
        map.put("url",amazonS3.getUrl(bucket, s3FileName).toString());

        return map;
    }

    public void deleteImage(String objectKey){

        final AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_NORTHEAST_2).build();

        amazonS3.deleteObject(bucket, objectKey);

    }
}

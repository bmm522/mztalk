//package com.mztalk.resource.service.impl;
//
//
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class DeleteImageServiceImpl {
//
//    @Value("${cloud.aws.region.static}")
//    private String region;
//   private final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(region).build();
//}

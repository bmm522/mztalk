package com.mztalk.story.client;



import com.mztalk.story.domain.follow.dto.ProfileImagesResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="mztalk-resource-server")
public interface ResourceServerClient {

   @GetMapping("/resource/main-image")
   ProfileImagesResponseDto getMainImage(@RequestParam("bNo")long bNo, @RequestParam("serviceName")String serviceName);




}

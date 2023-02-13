package com.mztalk.story.client;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="resource")
public interface ResourceServerClient {



}

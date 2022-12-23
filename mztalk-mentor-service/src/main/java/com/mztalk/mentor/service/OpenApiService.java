package com.mztalk.mentor.service;

import org.springframework.util.MultiValueMap;

import java.util.concurrent.ConcurrentHashMap;

public interface OpenApiService {
    void requestOpenApiAccessToken();

    boolean requestMatchAccountRealName(ConcurrentHashMap<String,String> accountMap);

    boolean getCrdiAccountInfo(Long crdiSeq);
}

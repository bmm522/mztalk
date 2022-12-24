package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.entity.OpenApiAccessToken;

import java.util.concurrent.ConcurrentHashMap;

public interface OpenApiService {

    OpenApiAccessToken requestOpenApiAccessToken();
    boolean requestMatchAccountRealName(ConcurrentHashMap<String,String> accountMap);

}

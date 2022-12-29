package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.dto.AccountInfoDto;
import com.mztalk.mentor.domain.OpenApiAccessToken;

import java.util.concurrent.ConcurrentHashMap;

public interface OpenApiService {

    OpenApiAccessToken requestOpenApiAccessToken();
    AccountInfoDto requestMatchAccountRealName(ConcurrentHashMap<String,String> accountMap);

}

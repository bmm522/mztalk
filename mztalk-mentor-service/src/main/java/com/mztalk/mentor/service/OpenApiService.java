package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.dto.AccountInfoReqDto;
import com.mztalk.mentor.domain.dto.AccountInfoResDto;
import com.mztalk.mentor.domain.OpenApiAccessToken;

import java.util.concurrent.ConcurrentHashMap;

public interface OpenApiService {

    OpenApiAccessToken requestOpenApiAccessToken();
    AccountInfoResDto requestMatchAccountRealName(AccountInfoReqDto accountInfoReqDto);

}

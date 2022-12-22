package com.mztalk.mentor.service;

import org.springframework.util.MultiValueMap;

public interface OpenApiService {
    void requestOpenApiAccessToken();

    boolean requestMatchAccountRealName(Long crdiSeq, String bankCode, String bankAccount, String realName, String birthday);

    boolean getCrdiAccountInfo(Long crdiSeq);
}

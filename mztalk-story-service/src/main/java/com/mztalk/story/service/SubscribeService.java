package com.mztalk.story.service;

import com.mztalk.story.domain.subscribe.dto.SubscribeRequestDto;
import com.mztalk.story.domain.subscribe.dto.SubscribeResponseDto;

public interface SubscribeService {
    Long save(SubscribeRequestDto subscribeRequestDto);

    int updateStatusByUserNo(Long userNo);

    SubscribeResponseDto findByUserNoAndRoleStatus(Long userNo);
}

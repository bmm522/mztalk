package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.dto.PaymentDto;
import com.mztalk.mentor.domain.entity.Result;

import java.util.concurrent.ConcurrentHashMap;

public interface PaymentService {
    Long save(ConcurrentHashMap<String,String> paymentMap);

    PaymentDto findById(Long id);

    Result findAll();

    Long cancel(Long id);
}

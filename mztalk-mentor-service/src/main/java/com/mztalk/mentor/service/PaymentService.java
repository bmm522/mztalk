package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.dto.PaymentDto;
import com.mztalk.mentor.domain.dto.PaymentReqDto;
import com.mztalk.mentor.domain.entity.Result;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface PaymentService {

    Long save(PaymentReqDto paymentReqDto);

    PaymentDto findById(Long id);

    List<PaymentDto> findAll();

    Long cancel(Long id);
}

package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.dto.PaymentDto;
import com.mztalk.mentor.domain.entity.Result;

public interface PaymentService {
    Long save(PaymentDto paymentDto);

    PaymentDto findById(Long id);

    Result findAll();

    Long cancel(Long id);
}

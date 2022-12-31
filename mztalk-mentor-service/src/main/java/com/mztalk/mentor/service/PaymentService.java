package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.dto.PaymentResDto;
import com.mztalk.mentor.domain.dto.PaymentReqDto;

import java.util.List;

public interface PaymentService {

    Long save(PaymentReqDto paymentReqDto);

    PaymentResDto findById(Long id);

    List<PaymentResDto> findAll();

    Long cancel(Long id);
}

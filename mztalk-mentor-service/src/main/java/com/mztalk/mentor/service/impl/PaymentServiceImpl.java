package com.mztalk.mentor.service.impl;

import com.mztalk.mentor.domain.dto.PaymentDto;
import com.mztalk.mentor.domain.entity.*;
import com.mztalk.mentor.exception.PaymentNotFoundException;
import com.mztalk.mentor.repository.BoardRepository;
import com.mztalk.mentor.repository.MenteeRepository;
import com.mztalk.mentor.repository.PaymentRepository;
import com.mztalk.mentor.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final MenteeRepository menteeRepository;
    private final BoardRepository boardRepository;

    @Override
    @Transactional
    public Long save(ConcurrentHashMap<String,String> paymentMap) {
        Long boardId = Long.parseLong(paymentMap.get("boardId"));
        Long userId = Long.parseLong(paymentMap.get("userId"));

        Board board = boardRepository.findBoardByBoardId(boardId);
        Mentee mentee = menteeRepository.findMenteeByUserId(userId);

        Payment payment = Payment.createPayment(paymentMap, mentee, board);
        Payment savedPayment = paymentRepository.save(payment);
        return savedPayment.getId();
    }

    @Override
    public PaymentDto findById(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFoundException("해당하는 결제내역이 존재하지 않습니다."));
        return new PaymentDto(payment);
    }

    @Override
    public Result findAll() {
        List<Payment> paymentList = paymentRepository.findAll();
        List<PaymentDto> collect = paymentList.stream().map(PaymentDto::new).collect(Collectors.toList());
        return new Result(collect);
    }

    @Override
    @Transactional
    public Long cancel(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFoundException("해당하는 결제내역이 존재하지 않습니다."));
        payment.cancelPayment();
        return payment.getId();
    }
}

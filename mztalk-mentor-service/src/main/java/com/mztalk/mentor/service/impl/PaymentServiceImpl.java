package com.mztalk.mentor.service.impl;

import com.mztalk.mentor.domain.dto.PaymentResDto;
import com.mztalk.mentor.domain.dto.PaymentReqDto;
import com.mztalk.mentor.domain.entity.*;
import com.mztalk.mentor.exception.PaymentNotFoundException;
import com.mztalk.mentor.repository.BoardRepository;
import com.mztalk.mentor.repository.MenteeRepository;
import com.mztalk.mentor.repository.ParticipantRepository;
import com.mztalk.mentor.repository.PaymentRepository;
import com.mztalk.mentor.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final MenteeRepository menteeRepository;
    private final BoardRepository boardRepository;
    private final ParticipantRepository participantRepository;

    @Override
    @Transactional
    public Long save(PaymentReqDto paymentReqDto) {
        Mentee mentee = menteeRepository.findMenteeByUserId(paymentReqDto.getUserId());
        Board board = boardRepository.findBoardByBoardId(paymentReqDto.getBoardId());

        Payment payment = paymentReqDto.toEntity();
        payment.addMentee(mentee);
        payment.addBoard(board);

        Payment savedPayment = paymentRepository.save(payment);
        return savedPayment.getId();
    }

    @Override
    public PaymentResDto findById(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFoundException("해당하는 결제내역이 존재하지 않습니다."));
        return new PaymentResDto(payment);
    }

    @Override
    public List<PaymentResDto> findAll() {
        List<Payment> paymentList = paymentRepository.findAll();
        List<PaymentResDto> collect = paymentList.stream().map(PaymentResDto::new).collect(Collectors.toList());
        return collect;
    }

    @Override
    @Transactional
    public Long cancel(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFoundException("해당하는 결제내역이 존재하지 않습니다."));
        Participant participant = participantRepository.findByPaymentId(id);
        paymentRepository.delete(payment);
        participantRepository.delete(participant);
        return payment.getId();
    }
}

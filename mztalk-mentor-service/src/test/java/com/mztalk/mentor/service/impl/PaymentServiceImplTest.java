//package com.mztalk.mentor.service.impl;
//
//import com.mztalk.mentor.domain.Status;
//import com.mztalk.mentor.domain.dto.PaymentDto;
//import com.mztalk.mentor.domain.entity.Payment;
//import com.mztalk.mentor.domain.entity.Result;
//import com.mztalk.mentor.repository.PaymentRepository;
//import com.mztalk.mentor.service.PaymentService;
//import lombok.RequiredArgsConstructor;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.*;
//
//@Transactional
//@RequiredArgsConstructor
//@SpringBootTest
//class PaymentServiceImplTest {
//    @Autowired
//    PaymentService paymentService;
//    @Autowired
//    PaymentRepository paymentRepository;
//
//    @Test
//    public void test() throws Exception {
//        //given
//        PaymentDto paymentDto1 = new PaymentDto();
//        paymentDto1.setPrice(10000);
//
//        PaymentDto paymentDto2 = new PaymentDto();
//        paymentDto2.setPrice(20000);
//        //when
//        Long savedId1 = paymentService.save(paymentDto1);
//        Long savedId2 = paymentService.save(paymentDto2);
//
//        PaymentDto savedDto1 = paymentService.findById(savedId1);
//        PaymentDto savedDto2 = paymentService.findById(savedId2);
//
//        paymentService.cancel(savedId1);
//        paymentService.cancel(savedId2);
//
//        Payment payment1 = paymentRepository.findById(savedId1).get();
//        Payment payment2 = paymentRepository.findById(savedId2).get();
//
//        List<Payment> paymentList = paymentRepository.findAll();
//
//        //then
//        assertThat(savedDto1.getPrice()).isEqualTo(paymentDto1.getPrice());
//        assertThat(savedDto2.getPrice()).isEqualTo(paymentDto2.getPrice());
//        assertThat(paymentList.size()).isEqualTo(2);
//        assertThat(savedDto1.getId()).isEqualTo(savedId1);
//        assertThat(savedDto2.getId()).isEqualTo(savedId2);
//        assertThat(payment1.getStatus()).isEqualTo(Status.NO);
//        assertThat(payment2.getStatus()).isEqualTo(Status.NO);
//    }
//
//
//
//
//
//}
package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.entity.Mentee;
import com.mztalk.mentor.domain.entity.Payment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class PaymentRepositoryTest {
    @Autowired
    private PaymentRepository paymentRepository;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void Test() throws Exception {
        //given
        Mentee mentee1 = Mentee.builder().nickname("memberA").build();
        Mentee mentee2 = Mentee.builder().nickname("memberB").build();

        em.persist(mentee1);
        em.persist(mentee2);
        em.flush();
        em.clear();

        //when
        Payment payment1 = Payment.builder().mentee(mentee1).price(10000).build();
        Payment payment2 = Payment.builder().mentee(mentee2).price(20000).build();

        Payment savedPayment1 = paymentRepository.save(payment1);
        Payment savedPayment2 = paymentRepository.save(payment2);

        Payment findPayment1 = paymentRepository.findById(payment1.getId()).get();
        Payment findPayment2 = paymentRepository.findById(payment2.getId()).get();
        List<Payment> paymentList = paymentRepository.findAll();

        //then
        assertThat(paymentList.size()).isEqualTo(2);
        assertThat(findPayment1).isEqualTo(savedPayment1);
        assertThat(findPayment2).isEqualTo(savedPayment2);
        assertThat(findPayment1.getMentee().getNickname()).isEqualTo("memberA");
        assertThat(findPayment2.getMentee().getNickname()).isEqualTo("memberB");
        assertThat(paymentList.get(0).getPrice()).isEqualTo(10000);
        assertThat(paymentList.get(1).getPrice()).isEqualTo(20000);
    }

}
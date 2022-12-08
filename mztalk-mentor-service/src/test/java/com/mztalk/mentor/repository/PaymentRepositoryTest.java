package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.Entity.Mentee;
import com.mztalk.mentor.domain.Entity.Payment;
import org.assertj.core.api.Assertions;
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
        Mentee mentee1 = new Mentee();
        Mentee mentee2 = new Mentee();

        mentee1.setNickname("memberA");
        mentee2.setNickname("memberB");

        em.persist(mentee1);
        em.persist(mentee2);
        em.flush();
        em.clear();

        //when
        Payment payment1 = new Payment();
        Payment payment2 = new Payment();

        payment1.setMentee(mentee1);
        payment2.setMentee(mentee2);

        payment1.setPrice(10000);
        payment2.setPrice(20000);

        paymentRepository.save(payment1);
        paymentRepository.save(payment2);

        Payment findPayment1 = paymentRepository.findById(payment1.getId()).get();
        Payment findPayment2 = paymentRepository.findById(payment2.getId()).get();
        List<Payment> paymentList = paymentRepository.findAll();
        //then
        assertThat(paymentList.size()).isEqualTo(2);
        assertThat(findPayment1.getMentee()).isEqualTo(mentee1);
        assertThat(findPayment2.getMentee()).isEqualTo(mentee2);
        assertThat(findPayment1.getMentee().getNickname()).isEqualTo("memberA");
        assertThat(findPayment2.getMentee().getNickname()).isEqualTo("memberB");
        assertThat(paymentList.get(0).getPrice()).isEqualTo(10000);
        assertThat(paymentList.get(1).getPrice()).isEqualTo(20000);
    }

}
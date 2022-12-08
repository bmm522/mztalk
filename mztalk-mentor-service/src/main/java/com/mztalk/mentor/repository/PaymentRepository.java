package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

}

package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.dto.PaymentDto;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors")
public class PaymentApiController {
    private final PaymentService paymentService;

    @PostMapping("/payment")
    public ResponseEntity<?> savePayment(@RequestBody ConcurrentHashMap<String,String> paymentMap){
        Long savedId = paymentService.save(paymentMap);
        return new ResponseEntity<>(new Result<>("해당 결제가 정상적으로 저장되었습니다.",savedId), HttpStatus.OK);
    }

    @GetMapping("/payment/{id}")
    public ResponseEntity<?> findPayment(@PathVariable("id")Long id){
        PaymentDto payment = paymentService.findById(id);
        return new ResponseEntity<>(new Result<>("해당 번호에 대한 결제 정보", payment), HttpStatus.OK);
    }

    @GetMapping("/payments")
    public ResponseEntity<?> findAll(){
        List<PaymentDto> payments = paymentService.findAll();
        return new ResponseEntity<>(new Result<>("멘토 서비스에서 결제된 모든 결제 정보", payments), HttpStatus.OK);
    }

    @PatchMapping("/payment/{id}")
    public Long cancelPayment(@PathVariable("id")Long id){
        return paymentService.cancel(id);
    }
}

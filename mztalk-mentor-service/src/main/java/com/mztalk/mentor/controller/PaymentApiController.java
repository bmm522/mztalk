package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.dto.PaymentDto;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors")
public class PaymentApiController {
    private final PaymentService paymentService;

    @PostMapping("/payment")
    public Long savePayment(@RequestBody ConcurrentHashMap<String,String> paymentDto){
        return paymentService.save(paymentDto);
    }

    @GetMapping("/payment/{id}")
    public PaymentDto findPayment(@PathVariable("id")Long id){
        return paymentService.findById(id);
    }

    @GetMapping("/payments")
    public Result findAll(){
        return paymentService.findAll();
    }

    @PatchMapping("/payment/{id}")
    public Long cancelPayment(@PathVariable("id")Long id){
        return paymentService.cancel(id);
    }
}

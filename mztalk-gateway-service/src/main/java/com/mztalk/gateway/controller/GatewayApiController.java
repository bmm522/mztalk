package com.mztalk.gateway.controller;

import com.mztalk.gateway.service.TrafficService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/gateway")
@RequiredArgsConstructor
public class GatewayApiController {


    private final TrafficService trafficService;
    @GetMapping("/traffic/{serviceName}")
    public List<ConcurrentHashMap<String, String>> getTraffic(@PathVariable("serviceName")String serviceName){
        System.out.println(serviceName);
        return trafficService.getTraffic(serviceName);
    }
}

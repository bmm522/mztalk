package com.mztalk.gateway.service;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface TrafficService {
    List<ConcurrentHashMap<String, String>> getTrafficOfServiceName(String serviceName);

    List<ConcurrentHashMap<String, String>> getTrafficOfServiceNameAndRequestTime(String requestTime);

    List<ConcurrentHashMap<String, String>> getTotalCount(String sixBefore, String fiveBefore, String fourBefore, String threeBefore, String twoBefore, String oneBefore, String today);
}

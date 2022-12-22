package com.mztalk.gateway.service;

import com.mztalk.gateway.domain.Result;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface TrafficService {
    Result<?> getTrafficOfServiceName(String serviceName);

    Result<?> getTrafficOfServiceNameAndRequestTime(String requestTime);

    Result<?> getTotalCount(String sixBefore, String fiveBefore, String fourBefore, String threeBefore, String twoBefore, String oneBefore, String today);
}

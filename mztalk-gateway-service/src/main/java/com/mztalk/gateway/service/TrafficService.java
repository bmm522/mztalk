package com.mztalk.gateway.service;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface TrafficService {
    List<ConcurrentHashMap<String, String>> getTraffic(String serviceName);
}

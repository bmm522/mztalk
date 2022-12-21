package com.mztalk.gateway.filter;

import com.mztalk.gateway.domain.entity.Traffic;
import com.mztalk.gateway.repository.TrafficRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FilterFactory {

    private final TrafficRepository trafficRepository;

    public void saveTraffic(String serviceName, String uri){
        trafficRepository.save(new Traffic(serviceName, uri));
    }
}

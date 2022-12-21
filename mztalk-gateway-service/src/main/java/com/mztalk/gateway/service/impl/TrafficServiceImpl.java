package com.mztalk.gateway.service.impl;

import com.mztalk.gateway.domain.dto.TrafficCountDto;
import com.mztalk.gateway.repository.TrafficRepository;
import com.mztalk.gateway.service.TrafficService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class TrafficServiceImpl implements TrafficService {

    private final TrafficRepository trafficRepository;

    @Override
    public List<ConcurrentHashMap<String, String>> getTraffic(String serviceName) {

//        LocalDateTime startDatetime = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(0,0,0));
//        LocalDateTime endDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));
        List<TrafficCountDto> list = trafficRepository.getCountOfRequestTime(serviceName);
        System.out.println("리스트는 뽑아오니?");
       List<ConcurrentHashMap<String, String>> mapList = new ArrayList<>();
        for(TrafficCountDto trafficCountDto : list){
            ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
            map.put("count", String.valueOf(trafficCountDto.getCount()));
            map.put("requestTime",String.valueOf(trafficCountDto.getRequestTime()));
            mapList.add(map);
        }

        return mapList;
    }
}

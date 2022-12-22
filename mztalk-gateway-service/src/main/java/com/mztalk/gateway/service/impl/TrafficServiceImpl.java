package com.mztalk.gateway.service.impl;

import com.mztalk.gateway.domain.dto.TrafficCountDto;
import com.mztalk.gateway.domain.dto.TrafficOfRequestTimeDto;
import com.mztalk.gateway.repository.TrafficRepository;
import com.mztalk.gateway.service.TrafficService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class TrafficServiceImpl implements TrafficService {

    private final TrafficRepository trafficRepository;


    @Override
    public List<ConcurrentHashMap<String, String>> getTotalCount(String sixBefore, String fiveBefore, String fourBefore, String threeBefore, String twoBefore, String oneBefore, String today) {
        List<TrafficCountDto> list = trafficRepository.getTotalCountOfRequestTime();
        List<ConcurrentHashMap<String, String>> mapList = new ArrayList<>();
        for(int i = 0 ; i < list.size() ; i++){

            ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

            if(list.get(i).getRequestTime().equals(sixBefore)){
                map.put("count", String.valueOf(list.get(i).getCount()));
                map.put("requestTime",String.valueOf(list.get(i).getRequestTime()));
                mapList.add(map);
            }

            if(list.get(i).getRequestTime().equals(fiveBefore)){
                map.put("count", String.valueOf(list.get(i).getCount()));
                map.put("requestTime",String.valueOf(list.get(i).getRequestTime()));
                mapList.add(map);
            }

            if(list.get(i).getRequestTime().equals(fourBefore)){
                map.put("count", String.valueOf(list.get(i).getCount()));
                map.put("requestTime",String.valueOf(list.get(i).getRequestTime()));
                mapList.add(map);
            }


            if(list.get(i).getRequestTime().equals(threeBefore)){
                map.put("count", String.valueOf(list.get(i).getCount()));
                map.put("requestTime",String.valueOf(list.get(i).getRequestTime()));
                mapList.add(map);
            }
            if(list.get(i).getRequestTime().equals(twoBefore)){
                map.put("count", String.valueOf(list.get(i).getCount()));
                map.put("requestTime",String.valueOf(list.get(i).getRequestTime()));
                mapList.add(map);
            }
            if(list.get(i).getRequestTime().equals(oneBefore)){
                map.put("count", String.valueOf(list.get(i).getCount()));
                map.put("requestTime",String.valueOf(list.get(i).getRequestTime()));
                mapList.add(map);
            }

            if(list.get(i).getRequestTime().equals(today)){
                map.put("count", String.valueOf(list.get(i).getCount()));
                map.put("requestTime",String.valueOf(list.get(i).getRequestTime()));
                mapList.add(map);
            }


        }

        return mapList;
    }


    @Override
    public List<ConcurrentHashMap<String, String>> getTrafficOfServiceName(String serviceName) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date(System.currentTimeMillis());
        System.out.println("오늘 날짜 : " + formatter.format(date));

        List<TrafficCountDto> list = trafficRepository.getCountOfRequestTime(serviceName);

        List<ConcurrentHashMap<String, String>> mapList = new ArrayList<>();
        for(TrafficCountDto trafficCountDto : list){
            ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
            map.put("count", String.valueOf(trafficCountDto.getCount()));
            map.put("requestTime",String.valueOf(trafficCountDto.getRequestTime()));
            mapList.add(map);
        }

        return mapList;
    }

    @Override
    public List<ConcurrentHashMap<String, String>> getTrafficOfServiceNameAndRequestTime(String requestTime) {

        List<TrafficOfRequestTimeDto> list = trafficRepository.getCountOfServiceNameAndRequestTime(requestTime);
        List<ConcurrentHashMap<String, String>> mapList = new ArrayList<>();
        for(TrafficOfRequestTimeDto trafficOfRequestTimeDto : list){
            ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
            map.put("count", String.valueOf(trafficOfRequestTimeDto.getCount()));
            map.put("requestTime", trafficOfRequestTimeDto.getRequestTime());
            map.put("serviceName", trafficOfRequestTimeDto.getServiceName());

            mapList.add(map);
        }
        return mapList;
    }



    private ConcurrentHashMap<String, String> putMap(ConcurrentHashMap<String, String> map, String count, String requestTime){

    }
}

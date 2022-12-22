package com.mztalk.gateway.service.impl;

import com.mztalk.gateway.domain.Result;
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
    public Result<?> getTotalCount(String sixBefore, String fiveBefore, String fourBefore, String threeBefore, String twoBefore, String oneBefore, String today) {
        List<TrafficCountDto> list = trafficRepository.getTotalCountOfRequestTime();
        List<ConcurrentHashMap<String, String>> mapList = new ArrayList<>();

        for(int i = 0 ; i < list.size() ; i++){

            ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

            if(list.get(i).getRequestTime().equals(sixBefore)){
                mapList.add(putMap(map,String.valueOf(list.get(i).getCount()), String.valueOf(list.get(i).getRequestTime())));
            }

            if(list.get(i).getRequestTime().equals(fiveBefore)){
                mapList.add(putMap(map,String.valueOf(list.get(i).getCount()), String.valueOf(list.get(i).getRequestTime())));
            }

            if(list.get(i).getRequestTime().equals(fourBefore)){
                mapList.add(putMap(map,String.valueOf(list.get(i).getCount()), String.valueOf(list.get(i).getRequestTime())));
            }

            if(list.get(i).getRequestTime().equals(threeBefore)){
                mapList.add(putMap(map,String.valueOf(list.get(i).getCount()), String.valueOf(list.get(i).getRequestTime())));
            }

            if(list.get(i).getRequestTime().equals(twoBefore)){
                mapList.add(putMap(map,String.valueOf(list.get(i).getCount()), String.valueOf(list.get(i).getRequestTime())));
            }

            if(list.get(i).getRequestTime().equals(today)){
                System.out.println(today);
                mapList.add(putMap(map,String.valueOf(list.get(i).getCount()), String.valueOf(list.get(i).getRequestTime())));
            }

            if(list.get(i).getRequestTime().equals(oneBefore)){
                System.out.println(oneBefore);
                mapList.add(putMap(map,String.valueOf(list.get(i).getCount()), String.valueOf(list.get(i).getRequestTime())));
            }




        }

        return new Result<>(mapList);
    }


    @Override
    public Result<?> getTrafficOfServiceName(String serviceName) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date(System.currentTimeMillis());
        System.out.println("오늘 날짜 : " + formatter.format(date));

        List<TrafficCountDto> list = trafficRepository.getCountOfRequestTime(serviceName);

        List<ConcurrentHashMap<String, String>> mapList = new ArrayList<>();
        for(TrafficCountDto trafficCountDto : list){
            ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
            mapList.add(putMap(map, String.valueOf(trafficCountDto.getCount()), trafficCountDto.getRequestTime()));
        }

        return new Result<>(mapList);
    }

    @Override
    public Result<?> getTrafficOfServiceNameAndRequestTime(String requestTime) {

        List<TrafficOfRequestTimeDto> list = trafficRepository.getCountOfServiceNameAndRequestTime(requestTime);
        List<ConcurrentHashMap<String, String>> mapList = new ArrayList<>();
        for(TrafficOfRequestTimeDto trafficOfRequestTimeDto : list){
            ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
            map.put("count", String.valueOf(trafficOfRequestTimeDto.getCount()));
            map.put("requestTime", trafficOfRequestTimeDto.getRequestTime());
            map.put("serviceName", trafficOfRequestTimeDto.getServiceName());

            mapList.add(map);
        }
        return new Result<>(mapList);
    }



    private ConcurrentHashMap<String, String> putMap(ConcurrentHashMap<String, String> map, String count, String requestTime){
        map.put("count", count);
        map.put("requestTime",requestTime);
        return map;
    }
}

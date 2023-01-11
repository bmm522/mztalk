package com.mztalk.gateway.repository;

import com.mztalk.gateway.domain.dto.TrafficCountDto;
import com.mztalk.gateway.domain.dto.TrafficOfRequestTimeDto;
import com.mztalk.gateway.domain.entity.Traffic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface TrafficRepository extends JpaRepository<Traffic, Long>, TrafficCustomRepository {


    @Query(value = "SELECT count(*) as count, t.request_Time as requestTime " +
            "FROM traffic t " +
            "GROUP BY t.request_Time", nativeQuery = true)
    List<TrafficCountDto> getTotalCountOfRequestTime();

    @Query(value = "SELECT COUNT(*) AS count, t.request_Time As requestTime " +
            "FROM traffic t " +
            "WHERE t.service_Name = :serviceName " +
            "GROUP BY t.request_Time", nativeQuery = true)
    List<TrafficCountDto> getCountOfRequestTime(@RequestParam("serviceName") String serviceName);


    @Query(value = "SELECT COUNT(*) as count, t.request_Time as requestTime , t.service_Name as serviceName " +
            "FROM traffic t " +
            "WHERE request_Time = :requestTime " +
            "GROUP BY t.request_Time, t.service_Name", nativeQuery = true)
    List<TrafficOfRequestTimeDto> getCountOfServiceNameAndRequestTime(@RequestParam("requestTime") String requestTime);



}

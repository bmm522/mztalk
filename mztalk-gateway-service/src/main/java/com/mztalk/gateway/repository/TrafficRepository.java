package com.mztalk.gateway.repository;

import com.mztalk.gateway.domain.dto.TrafficCountDto;
import com.mztalk.gateway.domain.dto.TrafficOfRequestTimeDto;
import com.mztalk.gateway.domain.entity.Traffic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface TrafficRepository extends JpaRepository<Traffic, Long>, TrafficCustomRepository {


    @Query(value = "SELECT count(*) as count, t.requestTime as requestTime " +
            "FROM traffic t " +
            "GROUP BY t.requestTime", nativeQuery = true)
    List<TrafficCountDto> getTotalCountOfRequestTime();

    @Query(value = "SELECT COUNT(*) AS count, t.requestTime As requestTime " +
            "FROM Traffic t " +
            "WHERE t.serviceName = :serviceName " +
            "GROUP BY t.requestTime", nativeQuery = true)
    List<TrafficCountDto> getCountOfRequestTime(@RequestParam("serviceName") String serviceName);


    @Query(value = "SELECT COUNT(*) as count, t.requestTime as requestTime , t.serviceName as serviceName " +
            "FROM traffic t " +
            "WHERE requestTime = :requestTime " +
            "GROUP BY t.requestTime, t.serviceName", nativeQuery = true)
    List<TrafficOfRequestTimeDto> getCountOfServiceNameAndRequestTime(@RequestParam("requestTime") String requestTime);



}

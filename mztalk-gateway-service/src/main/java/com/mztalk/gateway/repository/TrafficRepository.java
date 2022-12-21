package com.mztalk.gateway.repository;

import com.mztalk.gateway.domain.dto.TrafficCountDto;
import com.mztalk.gateway.domain.entity.Traffic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface TrafficRepository extends JpaRepository<Traffic, Long>, TrafficCustomRepository {


    @Query(value = "SELECT COUNT(*) AS count, t.requestTime As requestTime FROM Traffic t where t.serviceName = :serviceName GROUP BY t.requestTime", nativeQuery = true)
    List<TrafficCountDto> getCountOfRequestTime(@RequestParam("serviceName") String serviceName);

//    List<Traffic> findByRequestTimeBetween(LocalDateTime startDatetime, LocalDateTime endDatetime);

//    long countByServiceNameBetween(String serviceName, LocalDateTime startDatetime, LocalDateTime endDatetime);
}

package com.mztalk.main.domain.subscribe.repository;


import com.mztalk.main.domain.subscribe.dto.SubscribeRequestDto;
import com.mztalk.main.domain.subscribe.dto.SubscribeResponseDto;
import com.mztalk.main.domain.subscribe.entity.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

//    @Query("SELECT COUNT(s) FROM Subscribe s WHERE s.vipDate = CURRENT_DATE and s.userNo = :userNo")
//    Long findAllByDateBeforeToday(@Param("userNo") Long userNo);

    @Modifying
    @Query(value = "UPDATE Subscribe s SET s.roleStatus = 'USER' WHERE s.vipDate < CURRENT_TIMESTAMP and s.userNo = :userNo and s.roleStatus ='VIP'", nativeQuery = true)
    int updateStatusByUserNo(@Param("userNo") Long userNo);


}

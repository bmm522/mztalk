package com.mztalk.main.repository;

import com.mztalk.main.domain.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Modifying
    @Query(value="INSERT INTO follow(fromUserId,toUserId) VALUES(:fromUserId, :toUserId)", nativeQuery=true)
    void mFollow(@Param("fromUserId")Long fromUserNo, @Param("toUserId") Long toUserNo);






}

package com.mztalk.main.domain.follow.repository;

import com.mztalk.main.domain.follow.entity.FollowTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowTestRepository extends JpaRepository<FollowTest, Long> {

    @Modifying
    @Query(value="INSERT INTO FOLLOWTEST(fromUserId,toUserId) VALUES(:fromUserId, :toUserId)", nativeQuery=true)
    void mFollow(@Param("fromUserId") Long fromUserId, @Param("toUserId") Long toUserId);
}

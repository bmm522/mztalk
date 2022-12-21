package com.mztalk.main.domain.follow.repository;

import com.mztalk.main.domain.follow.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
   // Optional<Follow> findByToUserIdAndFromUserId(Long toUserId, Long fromUserId);


    @Modifying
    @Query(value="INSERT INTO FOLLOW(fromUserId,toUserId) VALUES(:fromUserId, :toUserId)", nativeQuery=true)
    void mFollow(@Param("fromUserId") Long fromUserId, @Param("toUserId") Long toUserId);

    @Modifying
    @Query(value="DELETE FROM FOLLOW WHERE fromUserId = :fromUserId AND toUserId = :toUserId", nativeQuery=true)
    void mUnFollow(@Param("fromUserId") Long fromUserId, @Param("toUserId") Long toUserId);
    //1(변경된 행의 개수가 리턴됨) , -1(오류),  0(insert가 안됨)

    @Query(value = "select count(*) from follow where fromUserId = :principalId AND toUserId = :userId", nativeQuery = true)
    int mFollowState(int principalId, int userId);

    @Query(value = "select count(*) from follow where fromUserId = :userId", nativeQuery = true)
    int mFollowCount(int userId);


//    @Modifying
//    @Query(value="INSERT INTO follow(fromUserId,toUserId) VALUES(:fromUserId, :toUserId)", nativeQuery=true)
//    void mFollow(@Param("fromUserId")Long fromUserNo, @Param("toUserId") Long toUserNo);






}

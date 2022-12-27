package com.mztalk.main.domain.follow.repository;

import com.mztalk.main.domain.follow.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
   // Optional<Follow> findByToUserIdAndFromUserId(Long toUserId, Long fromUserId);


    @Modifying
    @Query(value="INSERT INTO FOLLOW(fromUserId,toUserId, followStatus) VALUES(:fromUserId, :toUserId, 'FOLLOWING')", nativeQuery=true)
    void mFollow(@Param("fromUserId") Long fromUserId, @Param("toUserId") Long toUserId);

    @Modifying
    @Query(value="DELETE FROM FOLLOW WHERE fromUserId = :fromUserId AND toUserId = :toUserId", nativeQuery=true)
    void mUnFollow(@Param("fromUserId") Long fromUserId, @Param("toUserId") Long toUserId);
    //1(변경된 행의 개수가 리턴됨) , -1(오류),  0(insert가 안됨)

    //@Query(value = "select count(*) from follow where fromUserId = :principalId AND toUserId = :userId", nativeQuery = true)
    //int mFollowState(int principalId, int userId);

    //@Query(value = "select count(*) from follow where fromUserId = :userId", nativeQuery = true)
    //int mFollowCount(int userId);


    @Query(value = "SELECT * from Follow f  where f.fromUserId = :fromUserId and followStatus != 'ONSELF'", nativeQuery = true)
    List<Follow> getListByFromUserId(Long fromUserId);


    @Query(value = "SELECT * from Follow f  where f.toUserId = :toUserId and followStatus != 'ONSELF'", nativeQuery = true)
    List<Follow> getListByToUserId(Long toUserId);

    Long countByToUserId(long own);    // 팔로워 수 (follower)
    Long countByFromUserId(long own);  // 팔로우 수 (following)


    @Modifying
    @Query(value="INSERT INTO FOLLOW(fromUserId,toUserId, followStatus) VALUES(:fromUserId, :toUserId, 'ONSELF')", nativeQuery=true)
    void mFollowStatus(Long fromUserId, Long toUserId);

//    List<Follow> findAllByFromUser(long own); // 사용자가 팔로우한 관계를 가져옴
//    List<Follow> findAllByToUser(long own);	 // 사용자를 팔로우하는 관계를 가져옴

}

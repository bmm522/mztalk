package com.mztalk.main.domain.reply.repository;

import com.mztalk.main.domain.reply.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ReplyRepository extends JpaRepository <Reply, Long> {


    @Modifying
    @Query("UPDATE Reply r SET r.replyNickname = :nickname WHERE r.replyUserNo = :userNo")
    int updateNickname(@Param("userNo") Long userNo, @Param("nickname") String nickname);
}

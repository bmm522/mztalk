package com.mztalk.story.repository;

import com.mztalk.story.domain.reply.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository <Reply, Long> {
    Reply findByReplyUserNo(Long replyUserNo);


//    @Modifying
//    @Query("UPDATE Reply r SET r.replyNickname = :nickname WHERE r.replyUserNo = :userNo")
//    int updateNickname(@Param("userNo") Long userNo, @Param("nickname") String nickname);
}

package com.mztalk.main.domain.reply.repository;

import com.mztalk.main.domain.reply.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository <Reply, Long> {


}

package com.mztalk.main.repository;

import com.mztalk.main.domain.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository <Reply, Long> {

}

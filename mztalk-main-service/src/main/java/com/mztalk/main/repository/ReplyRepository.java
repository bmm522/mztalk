package com.mztalk.main.repository;

import com.mztalk.main.domain.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ReplyRepository extends JpaRepository <Reply, Long> {


}

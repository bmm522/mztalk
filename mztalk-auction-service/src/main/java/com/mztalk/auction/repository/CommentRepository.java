package com.mztalk.auction.repository;

import com.mztalk.auction.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CustomAuctionRepository {

}

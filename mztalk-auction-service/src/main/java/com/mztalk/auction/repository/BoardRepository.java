package com.mztalk.auction.repository;

import com.mztalk.auction.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>, CustomAuctionRepository {


}

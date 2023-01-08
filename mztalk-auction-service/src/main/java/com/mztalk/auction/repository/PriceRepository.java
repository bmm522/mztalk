package com.mztalk.auction.repository;

import com.mztalk.auction.domain.dto.PriceDto;
import com.mztalk.auction.domain.entity.Board;
import com.mztalk.auction.domain.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long>, CustomAuctionRepository {

//    Price findByBoardId(Long bId);
}

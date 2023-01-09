package com.mztalk.auction.repository;

import com.mztalk.auction.domain.dto.PriceDto;
import com.mztalk.auction.domain.entity.Board;
import com.mztalk.auction.domain.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long>, CustomAuctionRepository {

//    List<Price> findByBoardId(Long bId);

}

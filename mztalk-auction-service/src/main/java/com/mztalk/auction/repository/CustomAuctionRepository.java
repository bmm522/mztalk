package com.mztalk.auction.repository;

import com.mztalk.auction.domain.dto.BoardDto;
import com.mztalk.auction.domain.entity.Board;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public interface CustomAuctionRepository {
    int boardUpdate(Long bId, BoardDto boardDto);

    int deleteBoard(Long bId);

    int updatePrice(Long bId, BoardDto boardDto);
}

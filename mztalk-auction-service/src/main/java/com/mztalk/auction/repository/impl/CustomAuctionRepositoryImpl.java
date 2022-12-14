package com.mztalk.auction.repository.impl;

import com.mztalk.auction.domain.dto.BoardDto;
import com.mztalk.auction.domain.entity.Board;
import com.mztalk.auction.repository.CustomAuctionRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CustomAuctionRepositoryImpl implements CustomAuctionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int boardUpdate(Long bId, BoardDto boardDto) {
        return entityManager.createQuery("update Board b set b.title= :title, b.content= :content where b.bId = :bId")
                .setParameter("title", boardDto.getTitle())
                .setParameter("content", boardDto.getContent())
                .setParameter("bId", bId)
                .executeUpdate();
    }
}

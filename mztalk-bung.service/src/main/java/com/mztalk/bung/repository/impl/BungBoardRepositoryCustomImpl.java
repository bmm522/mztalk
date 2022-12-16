package com.mztalk.bung.repository.impl;

import com.mztalk.bung.domain.entity.QBungBoard;
import com.mztalk.bung.repository.BungBoardRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

public class BungBoardRepositoryCustomImpl implements BungBoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public BungBoardRepositoryCustomImpl(EntityManager entityManager){
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    QBungBoard bungBoard = QBungBoard.bungBoard;


}

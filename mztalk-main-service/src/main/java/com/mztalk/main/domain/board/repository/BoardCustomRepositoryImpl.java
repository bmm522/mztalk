package com.mztalk.main.domain.board.repository;

import com.mztalk.main.domain.board.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class BoardCustomRepositoryImpl implements BoardCustomRepository {


    @Autowired
    EntityManager entityManager;


    @Override
    public List<Board> findAllByOwn(Long own) {
        return entityManager.createQuery("SELECT b FROM Board b WHERE b.own = :own and b.status= 'YES' ORDER BY b.id DESC", Board.class)
                .setParameter("own", own)
                .getResultList();
    }

    @Override
    public List<Board> findByOwn(Long own) {
        return entityManager.createQuery("SELECT b FROM Board b WHERE b.own = :own and b.status= 'YES' and b.privacy ='SECRET' ORDER BY b.id DESC", Board.class)
                .setParameter("own", own)
                .getResultList();
    }


}

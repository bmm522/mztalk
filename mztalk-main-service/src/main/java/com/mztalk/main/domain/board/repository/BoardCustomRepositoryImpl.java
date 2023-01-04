package com.mztalk.main.domain.board.repository;

import com.mztalk.main.domain.board.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
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
    public Page<Board> findAllByOwn(Long own, Pageable pageable) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        int limit = pageable.getPageSize();

        List<Board> boards = entityManager.createQuery("SELECT b FROM Board b WHERE b.own = :own and b.status= 'YES' ORDER BY b.id DESC", Board.class)
                .setParameter("own", own)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();

        long total = entityManager.createQuery("SELECT COUNT(b) FROM Board b WHERE b.own = :own and b.status= 'YES'", Long.class)
                .setParameter("own", own)
                .getSingleResult();

        return new PageImpl<>(boards, pageable, total);
    }

    @Override
    public Page<Board> findAllByBoardStory(Long own, Pageable pageable) {
        List<Board> boards = entityManager.createQuery("select distinct b from Board b left join Follow f on(b.own = f.fromUserId) where b.status='YES' and b.privacy='PUBLIC' and f.fromUserId = :own ORDER BY  b.id desc", Board.class)
                .setParameter("own", own)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        long total = countBoards(own);
        return new PageImpl<>(boards, pageable, total);
    }

    private long countBoards(Long own) {
        return entityManager.createQuery("select count(distinct b) from Board b left join Follow f on(b.own = f.fromUserId) where b.status='YES' and b.privacy='PUBLIC' and f.fromUserId = :own", Long.class)
                .setParameter("own", own)
                .getSingleResult();
    }


}

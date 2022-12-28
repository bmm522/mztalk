package com.mztalk.mentor.repository.impl;

import com.mztalk.mentor.domain.SearchCondition;
import com.mztalk.mentor.domain.entity.Board;
import com.mztalk.mentor.domain.entity.Mentor;
import com.mztalk.mentor.domain.entity.QBoard;
import com.mztalk.mentor.repository.BoardRepositoryCustom;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {
    @Autowired
    private EntityManager entityManager;

    private final JPAQueryFactory queryFactory;
    public BoardRepositoryCustomImpl(EntityManager entityManager){
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    QBoard board = QBoard.board;

    @Override
    public List<Board> searchWithCondition(SearchCondition searchCondition) {
        return queryFactory
                .selectFrom(board)
                .where(
                                eqCategory(searchCondition.getCategory()).
                                and(lePrice(searchCondition.getSalary())).
                                and(afterNow(searchCondition.getNow())).
                                or(containsContent(searchCondition.getContent())).
                                or(containsTitle(searchCondition.getTitle())).
                                or(containsWriter(searchCondition.getNickname()))

                )
//                .where(board.payment.isNull())
                .fetch();
    }

    @Override
    public Mentor findMentorByBoardId(Long id) {
        Mentor mentor = entityManager.createQuery("select m from Board b join b.mentor m where b.id =:id", Mentor.class).
                setParameter("id", id).getSingleResult();
        return mentor;
    }

    //멘티가 본인이 신청한 멘토링 글에 대해 보는 메소드.
    @Override
    public List<Board> findBoardByUserId(Long userId, LocalDateTime now) {
        return entityManager.createQuery("select b from Board b join b.participant p where p.mentee.id =:userId and b.mentoringDate<:now", Board.class)
                .setParameter("userId", userId)
                .setParameter("now",now)
                .getResultList();
    }

    @Override
    public List<Board> latestBoard() {
        return entityManager.createQuery("select b from Board b order by b.lastModifiedDate desc", Board.class)
                .setFirstResult(0)
                .setMaxResults(3)
                .getResultList();
    }

    @Override
    public List<Board> findBoardByMentorId(Long mentorId) {
        return entityManager.createQuery("select b from Board b where b.mentor.id =:mentorId", Board.class)
                .setParameter("mentorId",mentorId)
                .getResultList();
    }

    private BooleanExpression afterNow(LocalDateTime now){
        return now != null ? board.mentoringDate.after(now) : null;
    }

    private BooleanExpression eqCategory(String category){
        return category != null ? board.category.eq(category) : null;
    }

    private BooleanExpression lePrice(Integer salary){
        return salary != null ? board.salary.lt(salary+1) : null;
    }

    private BooleanExpression containsContent(String content){
        return content != null ? board.content.contains(content) : null;
    }

    private BooleanExpression containsTitle(String title){
        return title != null ? board.title.contains(title) : null;
    }

    private BooleanExpression containsWriter(String nickname){
        return nickname != null ? board.nickname.contains(nickname) : null;
    }






}

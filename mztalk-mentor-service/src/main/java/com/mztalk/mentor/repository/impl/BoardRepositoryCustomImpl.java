package com.mztalk.mentor.repository.impl;

import com.mztalk.mentor.domain.SearchCondition;
import com.mztalk.mentor.domain.entity.Board;
import com.mztalk.mentor.domain.entity.Mentor;
import com.mztalk.mentor.domain.entity.QBoard;
import com.mztalk.mentor.domain.entity.QMentor;
import com.mztalk.mentor.repository.BoardRepositoryCustom;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {
    @Autowired
    private EntityManager entityManager;

    private final JPAQueryFactory queryFactory;
    public BoardRepositoryCustomImpl(EntityManager entityManager){
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    QBoard board = QBoard.board;
    QMentor mentor = QMentor.mentor;

    @Override
    public List<Board> searchWithCondition(SearchCondition searchCondition) {
        return queryFactory
                .selectFrom(board)
                .where(eqCategory(searchCondition.getCategory()),
                        lePrice(searchCondition.getSalary()+1),
                        containsContent(searchCondition.getContent()),
                        containsTitle(searchCondition.getTitle()))
                .fetch();
    }

    @Override
    public Mentor findMentorByBoardId(Long id) {
        Mentor mentor = entityManager.createQuery("select m from Board b join b.mentor where b.id =:id", Mentor.class).
                setParameter("id", id).getSingleResult();
        return mentor;
    }

    private BooleanExpression eqCategory(String category){
        return category != null ? board.category.eq(category) : null;
    }

    private BooleanExpression lePrice(Integer salary){
        return salary != null ? board.salary.lt(salary) : null;
    }

    private BooleanExpression containsContent(String content){
        return content != null ? board.content.contains(content) : null;
    }

    private BooleanExpression containsTitle(String title){
        return title != null ? board.title.contains(title) : null;
    }

//    private BooleanExpression eqWriter(String nickname){
//        return nickname != null ? board.mentor.nickname.like(nickname) : null;
//    }






}

package com.mztalk.main.domain.follow.repository;


import com.mztalk.main.domain.follow.dto.MatpalListResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class FollowCustomRepositoryImpl implements FollowCustomRepository {

    @Autowired
    EntityManager entityManager;

    private final JPAQueryFactory queryFactory;

    public FollowCustomRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<MatpalListResponseDto> findByMatpalList(Long fromUserId) {
        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        Query q = entityManager.createNativeQuery("select follow from (select f1.fromUserId, f1.toUserId, f1.postImageUrl, f1.followStatus, if(f2.fromUserId is null, false, true) as matpal from follow f1 left outer join follow f2 on f1.fromUserId=f2.toUserId and f1.toUserId= f2.fromUserId order by f1.id) as A where A.matpal = 1 and A.fromUserId =:fromUserId");
        List<MatpalListResponseDto> matpalListResponseDto = jpaResultMapper.list(q,MatpalListResponseDto.class );

        return matpalListResponseDto;
    }

}

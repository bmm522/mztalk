package com.mztalk.mentor.common;

import com.mztalk.mentor.domain.Entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    //카테고리 초기값 Insert
    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.insertCategory();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void insertCategory(){
            em.persist(new Category(1L,"국어"));
            em.persist(new Category(2L,"영어"));
            em.persist(new Category(3L,"수학"));
            em.persist(new Category(4L,"과학"));
            em.persist(new Category(5L,"사회"));
        }

    }

}

package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApplicationRepository extends JpaRepository<Application,Long>, ApplicationRepositoryCustom{

    @Query("select a from Application a where a.mentee.id =:userId")
    Application findApplicationByUserId(@Param("userId")Long userId);
}

package com.mztalk.main.domain.profile.repository;

import com.mztalk.main.domain.profile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    //@Query("select p from Profile p where p.own =:own")
    //Profile findTop1ByOrderByIdDesc(@Param("own")Long own);


    //Profile findTop1ByOrderByIdDesc(long own);
}

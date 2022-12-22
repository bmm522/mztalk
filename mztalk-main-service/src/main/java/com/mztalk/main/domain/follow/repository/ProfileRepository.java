package com.mztalk.main.domain.follow.repository;

import com.mztalk.main.domain.follow.entity.Profile;
import com.mztalk.main.domain.follow.vo.ProfileVo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {


}

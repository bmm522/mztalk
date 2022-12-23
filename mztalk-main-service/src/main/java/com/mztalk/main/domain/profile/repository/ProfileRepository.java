package com.mztalk.main.domain.profile.repository;

import com.mztalk.main.domain.profile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {


}

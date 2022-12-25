package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.entity.AccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountInfoRepository extends JpaRepository<AccountInfo,Long> {
}

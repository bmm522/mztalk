package com.mztalk.resource.repository;

import com.mztalk.resource.domain.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long>, FileCustomRepository {
}

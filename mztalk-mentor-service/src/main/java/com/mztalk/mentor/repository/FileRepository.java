package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.Entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File,Long> {

}

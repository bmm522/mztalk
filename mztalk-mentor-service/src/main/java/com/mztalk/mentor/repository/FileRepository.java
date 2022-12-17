package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.entity.File;
import com.mztalk.mentor.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File,Long> {

}

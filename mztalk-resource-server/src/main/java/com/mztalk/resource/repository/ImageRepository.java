package com.mztalk.resource.repository;

import com.mztalk.resource.domain.entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Images, Long>, ImageCustomRepository {

    void deleteByObjectKey(String objectKey);
}

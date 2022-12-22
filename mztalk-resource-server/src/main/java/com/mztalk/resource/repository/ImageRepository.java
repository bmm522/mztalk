package com.mztalk.resource.repository;

import com.mztalk.resource.domain.entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Images, Long>, ImageCustomRepository {

    void deleteByObjectKey(String objectKey);

    Images findByObjectKey(String objectKey);

    List<Images> findBybNo(long bNo);
}

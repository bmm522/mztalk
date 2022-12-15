package com.mztalk.resource.repository;

import com.mztalk.resource.domain.entity.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class FileCustomRepositoryImpl implements FileCustomRepository{


    @Autowired
    EntityManager entityManager;

    public  void commit(){
        entityManager.flush();
        entityManager.clear();
    }
    @Override
    public List<File> getFileInfo(long id) {
        return entityManager.createQuery("SELECT f FROM File f WHERE f.id = :id", File.class)
                .setParameter("id", id)
                .getResultList();
    }
}

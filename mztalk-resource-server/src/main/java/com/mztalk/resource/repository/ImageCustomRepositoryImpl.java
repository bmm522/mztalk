package com.mztalk.resource.repository;

import com.mztalk.resource.domain.dto.ImagesDto;
import com.mztalk.resource.domain.entity.Images;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ImageCustomRepositoryImpl implements ImageCustomRepository{

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Images> getImageInfo(long bNo, String serviceName) {
        return entityManager.createQuery("SELECT i FROM Images i WHERE i.bNo = :bNo AND i.serviceName = :serviceName AND i.status = 'Y'", Images.class)
                .setParameter("bNo", bNo)
                .setParameter("serviceName", serviceName)
                .getResultList();
    }

    @Override
    public Images getMainImage(long bNo, String serviceName) {
        return entityManager.createQuery("SELECT i FROM Images i WHERE i.bNo = :bNo AND i.serviceName = :serviceName AND i.status = 'Y' AND i.imageLevel = 0", Images.class)
                .setParameter("bNo", bNo)
                .setParameter("serviceName", serviceName)
                .getSingleResult();
    }

    public  void commit(){
        entityManager.flush();
        entityManager.clear();
    }
}

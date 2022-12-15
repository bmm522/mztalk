package com.mztalk.resource.repository;

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
        return entityManager.createQuery("SELECT i " +
                                                                            "FROM Images i " +
                                                                            "WHERE i.bNo = :bNo AND i.serviceName = :serviceName AND i.status = 'Y'"
                                                                            , Images.class)
                .setParameter("bNo", bNo)
                .setParameter("serviceName", serviceName)
                .getResultList();
    }

    @Override
    public List<Images> getSubImages(long bNo, String serviceName) {
        return entityManager.createQuery("SELECT i " +
                                                                            "FROM Images i " +
                                                                            "WHERE i.bNo = :bNo AND i.serviceName = :serviceName AND i.imageLevel = 1"
                                                                            , Images.class)
                .setParameter("bNo", bNo)
                .setParameter("serviceName", serviceName)
                .getResultList();
    }



    @Override
    public Images getMainImage(long bNo, String serviceName) {
        return entityManager.createQuery("SELECT i " +
                                                                            "FROM Images i " +
                                                                            "WHERE i.bNo = :bNo AND i.serviceName = :serviceName AND i.status = 'Y' AND i.imageLevel = 0", Images.class)
                .setParameter("bNo", bNo)
                .setParameter("serviceName", serviceName)
                .getSingleResult();
    }

    public  void commit(){
        entityManager.flush();
        entityManager.clear();
    }

    @Override
    public List getObjectKeyList(long bNo, String serviceName) {
        return entityManager.createQuery("SELECT i.objectKey " +
                                                                            "FROM Images i " +
                                                                            "WHERE i.bNo = :bNo AND i.serviceName = :serviceName")
                .setParameter("bNo", bNo)
                .setParameter("serviceName", serviceName)
                .getResultList();
    }

    @Override
    public String getObjectKey(String imageName) {
        return entityManager.createQuery("SELECT i.objectKey " +
                                                                            "FROM Images i " +
                                                                            "WHERE i.imageName = :imageName"
                                                                            , String.class)
                .setParameter("imageName", imageName)
                .getSingleResult();

    }
    @Override
    public int changeMainImageToSubImage(long bNo, String serviceName) {
        return entityManager.createQuery("UPDATE Images i " +
                                                                            "SET i.imageLevel = 1 " +
                                                                            "WHERE i.bNo = :bNo AND i.serviceName = :serviceName AND i.imageLevel=0")
                .setParameter("bNo", bNo)
                .setParameter("serviceName", serviceName)
                .executeUpdate();
    }

    @Override
    public int changeSubImageToMainImage(String imageName) {
        return entityManager.createQuery("UPDATE Images i " +
                                                                            "SET i.imageLevel = 0 " +
                                                                            "WHERE i.imageName = :imageName")
                .setParameter("imageName", imageName)
                .executeUpdate();
    }


}

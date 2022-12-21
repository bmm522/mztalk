package com.mztalk.gateway.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class TrafficCustomRepositoryImpl implements TrafficCustomRepository{

    @Autowired
    EntityManager entityManager;

//    @Override
//    public List<Object> getCountOfRequestTime(String serviceName) {
//        return entityManager.createQuery("SELECT COUNT(t) AS count, t.requestTime FROM Traffic t WHERE serviceName = :serviceName GROUP BY requestTime", Object.class)
//                .setParameter("serviceName", serviceName)
//                .getResultList();
//    }

//    @Override
//    public long getTraffic(String serviceName) {
//        return entityManager.createQuery("SELECT COUNT(*) FROM Traffic t where t.serviceName = :serviceName")
//                .setParameter("serviceName" , serviceName)
//
//    }
}

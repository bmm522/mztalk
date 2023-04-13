package com.mztalk.story.domain.serviceinfo.repository;


import com.mztalk.story.domain.serviceinfo.entity.ServiceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceInfoRepository extends JpaRepository<ServiceInfo, Long> {

}

package com.mztalk.main.domain.serviceinfo.repository;


import com.mztalk.main.domain.serviceinfo.dto.ServiceInfoRequestDto;
import com.mztalk.main.domain.serviceinfo.entity.ServiceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceInfoRepository extends JpaRepository<ServiceInfo, Long> {

}

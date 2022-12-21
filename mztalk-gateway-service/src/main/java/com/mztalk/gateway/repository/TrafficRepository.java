package com.mztalk.gateway.repository;

import com.mztalk.gateway.domain.entity.Traffic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrafficRepository extends JpaRepository<Traffic, Long> {
}

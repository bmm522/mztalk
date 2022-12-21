package com.mztalk.bung.repository;

import com.mztalk.bung.domain.entity.BungAddBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BungAddBoardRepository extends JpaRepository<BungAddBoard, Long>, BungAddBoardRepositoryCustom {

}

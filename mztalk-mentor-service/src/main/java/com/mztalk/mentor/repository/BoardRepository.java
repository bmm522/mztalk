package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {

}
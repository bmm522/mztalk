package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long>, BoardRepositoryCustom{
    @Query("select b from Board b join fetch b.mentor m where b.status ='YES' and b.id =:id")
    Board findBoardByBoardId(@Param("id") Long id);

    List<Board> findByMentoringDateBefore(LocalDateTime now);

    @Query("select b from Board b join fetch b.participant p join fetch b.payment pay where p.mentee.id =:menteeId")
    List<Board> findBoardByMenteeId(@Param("menteeId")Long menteeId);

    @Query("select b from Board b join fetch b.mentor m where b.id=:boardId and m.userId=:userId")
    Board isOwner(@Param("userId") Long userId,@Param("boardId") Long boardId);
}

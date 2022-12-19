package com.mztalk.bung.service.impl;

import com.mztalk.bung.domain.BoardStatus;
import com.mztalk.bung.domain.dto.BungBoardDto;
import com.mztalk.bung.domain.entity.BungBoard;
import com.mztalk.bung.domain.entity.Result;
import com.mztalk.bung.exception.BoardException;
import com.mztalk.bung.repository.BungBoardRepository;
import com.mztalk.bung.repository.BungBoardRepositoryCustom;
import com.mztalk.bung.service.BungBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Component
@RequiredArgsConstructor
public class BungServiceImpl implements BungBoardService {

    private final BungBoardRepository bungRepository;

    private final BungBoardRepositoryCustom bungRepositoryCustom;

    // 메인 서비스 게시글 작성
    @Override
    @Transactional
    public Long mainInsertBoard(BungBoardDto bungBoardDto) {

        BungBoard bungBoardEntity = BungBoard.builder().
                boardId(bungBoardDto.getBoardId()).
                boardWriter(bungBoardDto.getBoardWriter()).
                boardTitle(bungBoardDto.getBoardTitle()).
                boardContent(bungBoardDto.getBoardContent()).
                deadlineDate(bungBoardDto.getDeadlineDate()).
                fullGroup(bungBoardDto.getFullGroup()).
                nowGroup(1L).
                createDate(bungBoardDto.getCreateDate()).
                modifyDate(bungBoardDto.getModifyDate()).
                boardCount(0L).
                boardStatus(BoardStatus.YES).
                category(bungBoardDto.getCategory()).
                build();

                return bungRepository.save(bungBoardEntity).getBoardId();
    }

    // 메인 서비스 게시글 조회
    @Override
    public Result mainSelectList() {
        List<BungBoard> bungBoards = bungRepository.findAll();
        List<BungBoardDto> collect = bungBoards.stream().map(BungBoardDto::new).collect(Collectors.toList());
        return new Result(collect);
    }

    // 메인 게시글 수정
    @Override
    @Transactional
    public Long mainBoardUpdate(Long bId, BungBoardDto bungBoardDto) {
        BungBoard saveBungBoard = bungRepository.findById(bId).orElseThrow(() -> new BoardException("해당 번호의 글이 존재하지 않습니다."));
        saveBungBoard.mainBoardUpdate(bungBoardDto);
        return saveBungBoard.getBoardId();

//        return bungRepositoryCustom.mainBoardUpdate(bId, bungBoardDto);
    }

    @Override
    @Transactional
    public Long mainBoardDelete(Long bId) {
        BungBoard deleteBungBoard = bungRepository.findById(bId).orElseThrow(() -> new BoardException("해당 번호의 글이 존재하지 않습니다."));
        deleteBungBoard.changeStatus();
        return deleteBungBoard.getBoardId();
    }

    @Override
    public BungBoardDto mainBoardSelect(Long bId) {
        BungBoard bungBoard = bungRepository.findById(bId).orElseThrow(() -> new BoardException("해당 번호의 글이 존재하지 않습니다."));
        BungBoardDto bungBoardDto = new BungBoardDto(bungBoard);
        return bungBoardDto;
    }

    @Override
    public int increaseCount(Long bId) {
        return bungRepositoryCustom.increaseCount(bId);
    }
}

package com.mztalk.bung.service.impl;

import com.mztalk.bung.domain.BoardStatus;
import com.mztalk.bung.domain.dto.BungBoardDto;
import com.mztalk.bung.domain.entity.BungBoard;
import com.mztalk.bung.domain.entity.Result;
import com.mztalk.bung.repository.BungBoardRepository;
import com.mztalk.bung.service.BungBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BungServiceImpl implements BungBoardService {

    private final BungBoardRepository bungRepository;

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

    @Override
    public Result mainSelectList() {
        List<BungBoard> bungBoards = bungRepository.findAll();
        List<BungBoardDto> collect = bungBoards.stream().map(BungBoardDto::new).collect(Collectors.toList());
        return new Result(collect);
    }


    @Override
    @Transactional
    public Long mainBoardUpdate(Long bId, BungBoardDto bungBoardDto) {
//        BungBoard saveBungBoard = bungRepository.findById(bId).orE;
    }


}

package com.mztalk.auction.service.impl;

import com.mztalk.auction.domain.dto.BoardRequestDto;
import com.mztalk.auction.domain.dto.BoardDto;
import com.mztalk.auction.domain.entity.Board;
import com.mztalk.auction.repository.BoardRepository;
import com.mztalk.auction.service.AuctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {

    private final BoardRepository boardRepository;

    @Transactional
    @Override
    public Long insertBoard(BoardRequestDto boardRequestDto) {

        return boardRepository.save(boardRequestDto.toEntity()).getBId();
    }

    @Override
    public int updateBoard(Long bId, BoardDto boardDto) {
        return boardRepository.boardUpdate(bId, boardDto);
    }

    @Override
    public List<Board> selectBoardList() {
        return boardRepository.findAll();
    }

    @Override
    public int deleteBoard(Long bId) {
        return boardRepository.deleteBoard(bId);
    }

    @Override
    public Board selectBoard(Long bId) {

        return boardRepository.findBybId(bId);
    }

    @Override
    public int updatePrice(Long bId, BoardDto boardDto) {
        return boardRepository.updatePrice(bId, boardDto);
    }

    @Override
    public int updateCount(Long bId) {
        return boardRepository.updateCount(bId);
    }

    @Override
    public ConcurrentHashMap<String, String> getRecentBoardNo() {
        long bNo =  boardRepository.getRecentBoardNo();
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("bNo", String.valueOf(bNo+1));
        return map;
    }


}

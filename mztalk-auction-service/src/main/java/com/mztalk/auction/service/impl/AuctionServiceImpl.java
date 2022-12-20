package com.mztalk.auction.service.impl;

import com.mztalk.auction.domain.Result;
import com.mztalk.auction.domain.dto.BoardListResponseDto;
import com.mztalk.auction.domain.dto.BoardRequestDto;
import com.mztalk.auction.domain.dto.BoardDto;
import com.mztalk.auction.domain.dto.CommentDto;
import com.mztalk.auction.domain.entity.Board;
import com.mztalk.auction.domain.entity.Comment;
import com.mztalk.auction.repository.BoardRepository;
import com.mztalk.auction.repository.CommentRepository;
import com.mztalk.auction.service.AuctionService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    //게시글 작성
    @Transactional
    @Override
    public Long insertBoard(BoardRequestDto boardRequestDto) {
       long bId = boardRepository.save(boardRequestDto.toEntity()).getBoardId();
        System.out.println("post : " + bId);
        return bId;
    }

    //게시글 수정
    @Override
    public int updateBoard(Long bId, BoardDto boardDto) {
        return boardRepository.boardUpdate(bId, boardDto);
    }

    //전체 게시글 조회
    @Override
    public Result<?> selectBoardList() {
        List<BoardListResponseDto> boardListResponseDtoList = new ArrayList<>();
        List<Board> boardList =  boardRepository.findAll();

        for(Board board : boardList){
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "text/html");
            System.out.println("list 가져오기 : " + board.getBoardId());
            ResponseEntity<String> response = new RestTemplate().exchange(
              "http://localhost:8000/resource/main-image?bNo="+board.getBoardId()+"&serviceName=auction",
              HttpMethod.GET,
              new HttpEntity<String>(headers),
              String.class
            );
            JSONObject jsonObject = new JSONObject(response.getBody());
            JSONObject jsonData = jsonObject.getJSONObject("data");
            String imageUrl = jsonData.getString("imageUrl");
            String imageName = jsonData.getString("objectKey");

            boardListResponseDtoList.add(new BoardListResponseDto(board, imageUrl, imageName));

        }


        return new Result<>(boardListResponseDtoList);

    }

    //게시물 삭제
    @Override
    public int deleteBoard(Long bId) {
        return boardRepository.deleteBoard(bId);
    }

    //특정 게시물 조회
    @Override
    public Board selectBoard(Long bId) {

        return boardRepository.findByBoardId(bId);
    }

    //입찰가
    @Override
    public int updatePrice(Long bId, BoardDto boardDto) {
        return boardRepository.updatePrice(bId, boardDto);
    }

    //조회수
    @Override
    public int updateCount(Long bId) {
        return boardRepository.updateCount(bId);
    }

    //최신 글 번호 받아오기
    @Override
    public ConcurrentHashMap<String, String> getRecentBoardNo() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        long bId = 0L;
        try{
            bId = boardRepository.findFirstByOrderByBoardIdDesc().getBoardId()+1;
        } catch (NullPointerException e){
             map.put("bId", "1");
             return map;
        }
        map.put("bId", String.valueOf(bId));
        return map;
    }

    //댓글 작성
    @Override
    public Comment insertComment(CommentDto commentDto, Long bId) {
        return commentRepository.save(commentDto.toEntity());
    }

    //댓글 수정
    @Override
    public int updateComment(Long cId, CommentDto commentDto) {
        return commentRepository.updateComment(cId, commentDto);
    }

    //댓글 삭제
    @Override
    public int deleteComment(Long cId, CommentDto commentDto) {
        return commentRepository.deleteComment(cId, commentDto);
    }


}

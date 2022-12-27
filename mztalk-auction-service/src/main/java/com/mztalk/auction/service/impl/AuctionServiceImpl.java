package com.mztalk.auction.service.impl;

import com.mztalk.auction.domain.Result;
import com.mztalk.auction.domain.dto.*;
import com.mztalk.auction.domain.entity.Board;
import com.mztalk.auction.domain.entity.Comment;
import com.mztalk.auction.repository.BoardRepository;
import com.mztalk.auction.repository.CommentRepository;
import com.mztalk.auction.service.AuctionService;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.methods.HttpHead;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
    public int updateBoard(Long bId, BoardEditDto boardEditDto) {
        return boardRepository.boardUpdate(bId, boardEditDto);
    }

    //전체 게시글 조회
    @Override
    public Result<?> selectBoardList() throws ParseException {
        List<BoardListResponseDto> boardListResponseDtoList = new ArrayList<>();
        List<Board> boardList =  boardRepository.selectBoardList();


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

            boardListResponseDtoList.add(new BoardListResponseDto(board, String.valueOf(getTimeDuration(board)),imageUrl, imageName));

        }


        return new Result<>(boardListResponseDtoList);

    }
    private Duration getTimeDuration(Board board) {
        return Duration.between(getLocalDateTime(board.getTimeLimit()), getLocalDateTime(board.getCurrentTime()));
    }

    private LocalDateTime getLocalDateTime(String time){
        return LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    //게시물 삭제
    @Override
    public int deleteBoard(Long bId, String writer) {
        System.out.println("service단 bId, writer 확인: " + bId + ", " + writer);
        return boardRepository.deleteBoard(bId, writer);
    }


    //게시글 검색
    @Override
    public Result<?> searchBoard(String keyword) throws ParseException {
        List<BoardListResponseDto> boardListResponseDtoList = new ArrayList<>();
        List<Board> boardList =  boardRepository.searchBoard(keyword);
        System.out.println("service 검색 리스트: " + boardList);

        for(Board board : boardList){
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "text/html");
            System.out.println("검색 list 가져오기 : " + board.getBoardId());
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

            boardListResponseDtoList.add(new BoardListResponseDto(board, String.valueOf(getTimeDuration(board)),imageUrl, imageName));

        }
        return new Result<>(boardListResponseDtoList);
    }


    //특정 게시물 조회
    @Override
    public BoardDetailResponseDto selectBoard(Long bId) {
        Board board = boardRepository.findByBoardId(bId);
        List<ConcurrentHashMap<String, String>> imageInfo = new ArrayList<>();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/html");
        ResponseEntity<String> response = new RestTemplate().exchange(
                "http://localhost:8000/resource/images?bNo="+bId+"&serviceName=auction",
                HttpMethod.GET,
                new HttpEntity<String>(headers),
                String.class
        );

        JSONObject jsonObject = new JSONObject(response.getBody());
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        for(int i = 0; i < jsonArray.length(); i++) {
            ConcurrentHashMap<String, String> imageMap = new ConcurrentHashMap<>();
            imageMap.put("imageUrl", jsonArray.getJSONObject(i).getString("imageUrl"));
            imageMap.put("objectKey", jsonArray.getJSONObject(i).getString("objectKey"));
            imageMap.put("imageName", jsonArray.getJSONObject(i).getString("imageName"));
            imageMap.put("imageLevel", jsonArray.getJSONObject(i).getString("imageLevel"));

            imageInfo.add(imageMap);
        }


        return new BoardDetailResponseDto(board, imageInfo);

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
    public Comment insertComment(CommentRequestDto commentRequestDto) {

        Board board = boardRepository.findByBoardId(commentRequestDto.getBoardId());

        Comment comment = Comment.builder()
                .board(board)
                .content(commentRequestDto.getContent())
                .writer(commentRequestDto.getWriter())
                .createDate(commentRequestDto.getCreateDate())
                .status("Y")
                .build();
        return commentRepository.save(comment);
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

    //댓글 전체 조회
    @Override
    public Result<?> selectCommentList() {
        List<CommentDto> commentDtoList = commentRepository.selectCommentList();
        return new Result<>(commentDtoList);
    }




}

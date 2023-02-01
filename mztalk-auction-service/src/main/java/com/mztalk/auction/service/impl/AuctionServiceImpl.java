package com.mztalk.auction.service.impl;

import com.mztalk.auction.domain.Result;
import com.mztalk.auction.domain.dto.*;
import com.mztalk.auction.domain.entity.Board;
import com.mztalk.auction.domain.entity.Comment;
import com.mztalk.auction.domain.entity.Price;
import com.mztalk.auction.repository.BoardRepository;
import com.mztalk.auction.repository.CommentRepository;
import com.mztalk.auction.repository.PriceRepository;
import com.mztalk.auction.service.AuctionService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


@Service
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final PriceRepository priceRepository;

    private HttpHeaders httpHeaders;

//    public AuctionServiceImpl(){
//        this.httpHeaders = new HttpHeaders();
//    }


    //게시글 작성
    @Transactional
    @Override
    public Long insertBoard(BoardRequestDto boardRequestDto) {
       long bId = boardRepository.save(boardRequestDto.toEntity()).getBoardId();
        return bId;
    }

    //게시글 수정
    @Override
    public int updateBoard(Long bId, BoardEditDto boardEditDto) {
        return boardRepository.boardUpdate(bId, boardEditDto);
    }

    //전체 게시글 조회
    @Override
    public Result<?> selectBoardList(int page) throws ParseException {
        Pageable pageable = PageRequest.of(page - 1, 6);
        Page<Board> boardPage = boardRepository.findByStatusOrderByBoardIdDesc("Y", pageable);
        return new Result<>(new ListOfBoardListResponseDto(boardPage, getTimeDurationList(boardPage),getImageInfoList(boardPage)));
    }

    //페이징
    @Override
    public Result<?> selectBoardListOfFront(int page) throws ParseException {
        System.out.println("page : " + page);
        Pageable pageable = PageRequest.of(page - 1, 3);
        Page<Board> boardList = boardRepository.findByStatusOrderByBoardIdDesc("Y", pageable);

        return new Result<>(new ListOfBoardListResponseDto(boardList, getTimeDurationList(boardList),getImageInfoList(boardList)));
    }

    //닉네임 변경
    @Override
    public void changedNickname(ChangedNicknameDto changedNicknameDto) {
        boardRepository.changedNickname(changedNicknameDto);
    }



    private LocalDateTime getLocalDateTime(String time){
        return LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    //게시물 삭제
    @Override
    public int deleteBoard(Long bId, String writer) {
        return boardRepository.deleteBoard(bId, writer);
    }


    //게시글 검색
    @Override
    public Result<?> searchBoard(String keyword, int page) throws ParseException {
        Pageable pageable = PageRequest.of(page - 1, 6);
        Page<Board> boardList =  boardRepository.searchBoard(keyword, pageable);
        return new Result<>(new ListOfBoardListResponseDto(boardList, getTimeDurationList(boardList),getImageInfoList(boardList)));
    }


    //특정 게시물 조회
    @Override
    public BoardDetailResponseDto selectBoard(Long bId) {
        Board board = boardRepository.findByBoardId(bId);
        List<ImageRestDto> imageInfoList = getDetailImageRestDto(board);
        return new BoardDetailResponseDto(board, imageInfoList, getTimeDurationDto(board));
    }

    //게시물 리스트 이미지 정보 호출
    private List<ImageRestDto> getImageInfoList(Page<Board> boards){
        ArrayList<ImageRestDto> imageRestDtoList = new ArrayList<>();

        for(Board board : boards){
            imageRestDtoList.add(getImageRestDto(board));
        }

        return imageRestDtoList;
    }



    //입찰가
    @Override
    public BoardPriceDto updatePrice(BoardPriceDto boardPriceDto) {
        boardRepository.updatePrice(boardPriceDto);

        Board board = boardRepository.findByBoardId(boardPriceDto.getBoardId());
        Price price = Price.builder()
                .board(board)
                .buyer(boardPriceDto.getBuyer())
                .currentPrice(boardPriceDto.getCurrentPrice())
                .build();
        priceRepository.save(price);
        return boardPriceDto;
    }

    //조회수
    @Override
    public int updateCount(Long bId, Long writer) {
        return boardRepository.updateCount(bId, writer);
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
    public CommentResponseDto insertComment(CommentRequestDto commentRequestDto) {
        Board board = boardRepository.findByBoardId(commentRequestDto.getBoardId());

        Comment comment = Comment.builder()
                .board(board)
                .content(commentRequestDto.getContent())
                .writer(commentRequestDto.getWriter())
                .createDate(commentRequestDto.getCreateDate())
                .status("Y")
                .userNo(commentRequestDto.getUserNo())
                .build();
        return new CommentResponseDto(commentRepository.save(comment));
    }

    //댓글 수정
    @Override
    public CommentResponseDto updateComment(Long cId, CommentUpdateRequestDto commentUpdateRequestDto) {
        int result = commentRepository.updateComment(cId, commentUpdateRequestDto);
        System.out.println(result);
        return selectComment(cId);
    }

    //댓글 삭제
    @Override
    public int deleteComment(Long cId) {
        return commentRepository.deleteComment(cId);
    }

    //댓글 전체 조회
    @Override
    public Result<?> selectCommentList(Long bId) {
        List<Comment> commentList = commentRepository.selectCommentList(bId);
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        for(Comment comment : commentList) {
            commentResponseDtoList.add(new CommentResponseDto(comment));
        }
        return new Result<>(commentResponseDtoList);
    }

    //특정 댓글 조회
    @Override
    public CommentResponseDto selectComment(Long commentId) {
        Comment comment = commentRepository.findByCommentId(commentId);
        System.out.println("comment: " + comment);
        return new CommentResponseDto(comment);
    }

    //마감 게시글 제외
    @Override
    public Result<?> selectCloseBoardList(int page) throws ParseException {
        List<BoardListResponseDto> boardListResponseDtoList = new ArrayList<>();
        Pageable pageable = PageRequest.of(page - 1, 6);
        Page<Board> boardList = boardRepository.findByIsCloseAndStatusOrderByBoardIdDesc("N", "Y", pageable);

        return new Result<>(new ListOfBoardListResponseDto(boardList, getTimeDurationList(boardList), getImageInfoList(boardList)));
    }


    //리스트 시간 계산
    private List<TimeDto> getTimeDurationList(Page<Board> boardList) {
//        LocalDateTime localDateTime = LocalDateTime.now();
        ArrayList<TimeDto> timeList = new ArrayList<>();
        for(Board board : boardList){
            TimeDto timeDto = getTimeDurationDto(board);
            timeList.add(timeDto);
        }
        return timeList;
    }

    //시간 계산
    private TimeDto getTimeDurationDto(Board board) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Duration duration = Duration.between(getLocalDateTime(board.getTimeLimit()), localDateTime);

        long hour = duration.getSeconds() / 3600;
        long minute = (duration.getSeconds() % 3600)/60 ;
        long second = minute / 60;

        ConcurrentHashMap<String, Long> timeMap = new ConcurrentHashMap<>();

        if(hour >= 0 && minute >= 0 && second >= 0) {
            timeMap.put("hour", 0L);
            timeMap.put("minute", 0L);
            timeMap.put("second", 0L);
            if(!board.getIsClose().equals("Y")){
                boardRepository.updateIsClose(board.getBoardId());
            }
        } else {
            timeMap.put("hour", hour);
            timeMap.put("minute", minute);
            timeMap.put("second", second);
        }

        return(new TimeDto(hour, minute, second));
    }

    //지금 마감시키기
    @Override
    public int closeBoard(BoardCloseDto boardCloseDto) {
        return boardRepository.closeBoard(boardCloseDto.getBoardId());
    }


    //입찰가 현황 받아오기
    @Override
    public Result<?> getCurrentPrice(Long bId) {
        List<Price> priceList = priceRepository.getCurrentPrice(bId);
        List<PriceDto> priceDtoList = new ArrayList<>();
        for(Price price : priceList) {
            priceDtoList.add(new PriceDto(price));
        }
        return new Result<>(priceDtoList);
    }

    //메인 이미지 정보
    private ImageRestDto getImageRestDto(Board board){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "text/html");
        return new ImageRestDto(new RestTemplate().exchange(
                "http://localhost:8000/resource/main-image?bNo=" + board.getBoardId() + "&serviceName=auction",
                HttpMethod.GET,
                new HttpEntity<String>(httpHeaders),
                String.class
        ));
    }

    //특정 게시물 이미지 정보
    private List<ImageRestDto> getDetailImageRestDto(Board board){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "text/html");
        ImageRestDto imageRestDto = new ImageRestDto();
        return imageRestDto.getImageRestDtoList(new RestTemplate().exchange(
                "http://localhost:8000/resource/images?bNo=" + board.getBoardId() + "&serviceName=auction",
                HttpMethod.GET,
                new HttpEntity<String>(httpHeaders),
                String.class
        ));
    }

}

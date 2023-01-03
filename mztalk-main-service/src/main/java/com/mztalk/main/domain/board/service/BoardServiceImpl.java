package com.mztalk.main.domain.board.service;

import com.mztalk.main.domain.board.dto.BoardDto;
import com.mztalk.main.domain.board.Board;
import com.mztalk.main.common.Result;
import com.mztalk.main.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    //퍼블릭글 불러오기
    @Override
    @Transactional(readOnly  = true)
    public Result findAllByOwn(Long own) {
        List<Board> boards = boardRepository.findAllByOwn(own);
        List<BoardDto> collect = boards.stream().map(BoardDto::new).collect(Collectors.toList());
        return new Result(collect);
    }

    //글쓰기
    @Override
    @Transactional
    public Board save(BoardDto boardDto) {return boardRepository.save(boardDto.toEntity());}

    //글수정
    @Override
    @Transactional
    public Long updateBoard(Long id, BoardDto boardDto) {
        Board savedBoard = boardRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다."));
        savedBoard.updateBoard(boardDto);
        return savedBoard.getId();
    }

    //글삭제(status만변화)
    @Override
    @Transactional
    public Long deleteBoard(Long id) {

        Board board = boardRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다."));
        board.changeStatus();
        return board.getId();

    }

    //메인화면 뿌려주기
    @Override
    @Transactional(readOnly = true)
    public Result findAllByBoardStory(Long own) {
        List<Board> boards = boardRepository.findAllByBoardStory(own);
        List<BoardDto> collect = boards.stream().map(BoardDto::new).collect(Collectors.toList());
        return new Result(collect);

    }

//    @Override
//    public Long findByUserNo(String nickname) {
//
//        HttpHeaders headerName = new HttpHeaders();
//        headerName.add("Content-type", "text/html");
//
//        //유저의이름
//        HttpHeaders headersNames = new HttpHeaders();
//        headersNames.add("Content-type", "text/html");
//
//        ResponseEntity<String> responseName = new RestTemplate().exchange(
//                "http://localhost:8000/login/user-info/" + own,
//                HttpMethod.GET,
//                new HttpEntity<String>(headerName),
//                String.class
//        );
//
//        JSONObject ownName = new JSONObject(responseName.getBody());
//        Long own = ownName.getLong("userNo");
//
//        System.out.println(own);
//
//        return own;
//    }


}

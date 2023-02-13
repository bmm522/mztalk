package com.mztalk.story.service.impl;

import com.mztalk.story.domain.board.dto.BoardDto;
import com.mztalk.story.domain.board.Board;
import com.mztalk.story.common.Result;
import com.mztalk.story.domain.board.dto.BoardNicknameModifyDto;
import com.mztalk.story.domain.board.dto.BoardRequestDto;
import com.mztalk.story.domain.board.dto.BoardResponseDto;
import com.mztalk.story.repository.BoardRepository;
import com.mztalk.story.domain.profile.dto.ProfileResponseDto;
import com.mztalk.story.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    private final RestTemplate restTemplate;


    //퍼블릭글 불러오기
    @Override
    @Transactional(readOnly = true)
    public Result findByStatusOrderByBoardIdDesc(Long own, int page) {

        Pageable pageable = PageRequest.of(page - 1, 4);
        Page<Board> boards = boardRepository.findByStatusOrderByBoardIdDesc(own, pageable);
        List<BoardResponseDto> boardDtos = boards.stream().map(b->new BoardResponseDto(b, new ProfileResponseDto())).collect(Collectors.toList());
        return new Result(boardDtos);
    }

    //글쓰기
    @Override
    @Transactional
    public Long save(BoardRequestDto boardRequestDto) {
        Board board = boardRequestDto.toEntity();
        return boardRepository.save(board).getId();
    }

    //글수정
    @Override
    @Transactional
    public Long updateBoard(Long id, BoardResponseDto boardResponseDto) {
        Board savedBoard = boardRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다."));
        savedBoard.updateBoard(boardResponseDto);
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
    public Result findAllByBoardStory(Long own, int page) {
        Pageable pageable = PageRequest.of(page-1, 4);
        Page<Board> boards = boardRepository.findAllByBoardStory(own, pageable);
        List<BoardDto> boardDtos = boards.stream()
                .map(board -> {
                    ProfileResponseDto profileResponseDto = new ProfileResponseDto();
                    try {
                        HttpHeaders headersImg = new HttpHeaders();
                        headersImg.add("Content-type", "text/html");

                        ResponseEntity<String> responseImg = new RestTemplate().exchange(
                                "http://localhost:8000/resource/main-image?bNo=" + own + "&serviceName=story",    //첫번째: url
                                HttpMethod.GET,
                                new HttpEntity<String>(headersImg),     //바디, 헤더 다 담기 가능/엔티티
                                String.class
                        );
                        JSONObject profileImage = new JSONObject(responseImg.getBody());
                        JSONObject profileData = profileImage.getJSONObject("data");
                        String imageUrlOwn = profileData.getString("imageUrl");
                        String imageName = profileData.getString("objectKey");

                        profileResponseDto = ProfileResponseDto.builder()
                                .postImageUrl(imageUrlOwn)
                                .profileImageName(imageName)
                                .build();
                    } catch (Exception e) {
                        String personalUrl = "https://mztalk-resource-server.s3.ap-northeast-2.amazonaws.com/7276284f-daed-4b0d-9ca3-7a7bb1930138-profile.png";
                        String imageName = "기본프로필사진";
                        profileResponseDto = ProfileResponseDto.builder()
                                .profileImageName(imageName)
                                .postImageUrl(personalUrl)
                                .build();
                    }
                    return new BoardDto(board, profileResponseDto);
                }).collect(Collectors.toList());
        return new Result(boardDtos);
    }

    @Override
    @Transactional
    public Long modifyNickname(BoardNicknameModifyDto boardNicknameModifyDto) {

        Board board = boardRepository.findByOwn(boardNicknameModifyDto.getOwn());
        board.modifyNickname(boardNicknameModifyDto.getWriter());

        return board.getOwn();

    }


}

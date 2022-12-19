package com.mztalk.bung.service.impl;

import com.mztalk.bung.domain.BoardStatus;
import com.mztalk.bung.domain.dto.BungBoardDto;
import com.mztalk.bung.domain.entity.BungBoard;
import com.mztalk.bung.domain.entity.Result;
import com.mztalk.bung.domain.response.BungBoardResponseDto;
import com.mztalk.bung.exception.BoardException;
import com.mztalk.bung.repository.BungBoardRepository;
import com.mztalk.bung.repository.BungBoardRepositoryCustom;
import com.mztalk.bung.service.BungBoardService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


@Service
@Component
@RequiredArgsConstructor
@Transactional
public class BungServiceImpl implements BungBoardService {

    private final BungBoardRepository bungRepository;


    // 메인 서비스 게시글 작성
    @Override
    @Transactional
    public Long mainInsertBoard(BungBoardDto bungBoardDto) {

        BungBoard bungBoardEntity = BungBoard.builder().
                boardId(bungBoardDto.getBoardId()).
                boardWriter(bungBoardDto.getBoardWriter()).
                boardTitle(bungBoardDto.getBoardTitle()).
                boardContent(bungBoardDto.getBoardContent()).
                deadlineDate(Date.valueOf(bungBoardDto.getDeadlineDate())).
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
        List<BungBoardResponseDto> bungBoardResponseDtoList = new ArrayList<>();
        for(BungBoard bungBoard : bungBoards){
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "text/html");
            System.out.println("service : " + bungBoard.getBoardTitle());
            ResponseEntity<String> response = new RestTemplate().exchange(
                    "http://localhost:8000/resource/main-image?bNo="+bungBoard.getBoardId()+"&serviceName=bung",
                    HttpMethod.GET,
                    new HttpEntity<String>(headers),
                    String.class
            );
            JSONObject jsonObject = new JSONObject(response.getBody());
            JSONObject jsonData = jsonObject.getJSONObject("data");
            String imageUrl = jsonData.getString("imageUrl");
            String imageName = jsonData.getString("objectKey");

        //   bungBoardResponseDtoList = bungBoards.stream().map(l -> new BungBoardResponseDto(bungBoard, imageUrl, imageName)).collect(Collectors.toList());

//            System.out.println(bungBoardResponseDtoList.get(1));
        bungBoardResponseDtoList.add(new BungBoardResponseDto(bungBoard, imageUrl, imageName));

        }
//        System.out.println(bungBoardResponseDtoList.get(0).getTitle());
//        System.out.println(bungBoardResponseDtoList.get(1).getTitle());
//        List<BungBoardResponseDto> list = bungBoards.stream().map(l ->  bungBoardResponseDtoList).collect(Collectors.toList());
//        List<BungBoardDto> collect = bungBoards.stream().map(BungBoardDto::new).collect(Collectors.toList());
        return new Result(bungBoardResponseDtoList);
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
        return bungRepository.increaseCount(bId);
    }

    @Override
    public ConcurrentHashMap<String, String> getRecentBoardNo() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("bId", String.valueOf(bungRepository.findFirstByOrderByBoardIdDesc().getBoardId()+1));
        return map;
    }
}

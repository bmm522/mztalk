package com.mztalk.bung.service.impl;

import com.mztalk.bung.domain.BoardStatus;
import com.mztalk.bung.domain.dto.BungAddBoardDto;
import com.mztalk.bung.domain.dto.BungBoardDto;
import com.mztalk.bung.domain.entity.BungAddBoard;
import com.mztalk.bung.domain.entity.BungBoard;
import com.mztalk.bung.domain.entity.Result;
import com.mztalk.bung.domain.response.BungBoardDetailResponseDto;
import com.mztalk.bung.domain.response.BungBoardResponseDto;
import com.mztalk.bung.exception.BoardException;
import com.mztalk.bung.repository.BungAddBoardRepository;
import com.mztalk.bung.repository.BungBoardRepository;
import com.mztalk.bung.repository.BungBoardRepositoryCustom;
import com.mztalk.bung.service.BungBoardService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


@Service
@Component
@RequiredArgsConstructor
@Transactional
public class BungServiceImpl implements BungBoardService {

    private final BungBoardRepository bungRepository;

    private final BungAddBoardRepository bungAddRepository;


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
            System.out.println(bungBoard.getBoardId());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "text/html");
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

            bungBoardResponseDtoList.add(new BungBoardResponseDto(bungBoard, imageUrl, imageName));

        }

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
    public BungBoardDetailResponseDto mainBoardSelect(Long bId) {
        BungBoard bungBoard = bungRepository.findById(bId).orElseThrow(() -> new BoardException("해당 번호의 글이 존재하지 않습니다."));
        List<ConcurrentHashMap<String, String>> mapList = new ArrayList<>();
//        List<List<ConcurrentHashMap<String,String>>> list = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/html");
        ResponseEntity<String> response = new RestTemplate().exchange(
                "http://localhost:8000/resource/images?bNo="+bId+"&serviceName=bung",
                HttpMethod.GET,
                new HttpEntity<String>(headers),
                String.class
        );

        JSONObject jsonObject = new JSONObject(response.getBody());
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        for(int i = 0; i < jsonArray.length() ; i ++){
            ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
            map.put("imageUrl", jsonArray.getJSONObject(i).getString("imageUrl"));
            map.put("objectKey", jsonArray.getJSONObject(i).getString("objectKey"));
            map.put("imageLevel", jsonArray.getJSONObject(i).getString("imageLevel"));
            mapList.add(map);
//            list.add(mapList);
        }
//        System.out.println(list);
        return new BungBoardDetailResponseDto(bungBoard, mapList);

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

    @Override
    @Transactional
    public Long addBungBoard(BungAddBoardDto bungAddBoardDto) {

        BungAddBoard bungAddBoardEntity = BungAddBoard.builder().
                addId(bungAddBoardDto.getAddId()).
                addContent(bungAddBoardDto.getAddContent()).
                addPhone(bungAddBoardDto.getAddPhone()).
                boardStatus(BoardStatus.YES).
                addNickName(bungAddBoardDto.getAddNickName()).
//                boardId(bungAddBoardDto.getBoardId()).
                build();

        return bungAddRepository.save(bungAddBoardEntity).getAddId();
    }

    @Override
    public Result addBungBoardsList() {
        List<BungAddBoard> bungAddBoard = bungAddRepository.findAll();
        List<BungAddBoardDto> collect = bungAddBoard.stream().map(BungAddBoardDto::new).collect(Collectors.toList());
        return new Result(collect);
    }

}
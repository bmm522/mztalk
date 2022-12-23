package com.mztalk.bung.service.impl;

import com.mztalk.bung.domain.BoardStatus;
import com.mztalk.bung.domain.dto.BungAddBoardDto;
import com.mztalk.bung.domain.dto.BungBoardDto;
import com.mztalk.bung.domain.entity.BungAddBoard;
import com.mztalk.bung.domain.entity.BungBoard;
import com.mztalk.bung.domain.entity.Result;
import com.mztalk.bung.domain.response.BungBoardDetailResponseDto;
import com.mztalk.bung.domain.response.BungBoardResponseDto;
import com.mztalk.bung.exception.AddBoardException;
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
import java.awt.geom.RectangularShape;
import java.sql.Date;
import java.util.*;
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
                createDate(bungBoardDto.getCreateDate()).
                modifyDate(bungBoardDto.getModifyDate()).
                boardCount(0L).
                boardStatus(BoardStatus.YES).
                category(bungBoardDto.getCategory()).
                build();

                bungRepository.save(bungBoardEntity);

        Long boardId = bungBoardEntity.getBoardId();

//        BungBoard bungBoard = bungRepository.findBungBoardByWriterBoardId(boardId);
        BungBoard bungBoard = bungRepository.findBungBoardByBoardId(boardId);

//        System.out.println(bungBoard.getBoardId());

        BungAddBoard bungAddBoardEntity = BungAddBoard.builder().
//                addId(bungAddBoardDto.getAddId()).
                addPhone(null).
                addNickName(bungBoardDto.getBoardWriter()).
                boardStatus(BoardStatus.YES).
                bungBoard(bungBoard).
                build();

        // 빌더로 add엔티티 만들어서
        //// bungAddRepository.save(bungAdd)

//        Long result1 = bungRepository.save(bungBoardEntity).getBoardId();
//        ConcurrentHashMap<String, String> bungAddBoardMap = new ConcurrentHashMap<>();
//        bungAddBoardMap.put("bId" , String.valueOf(result1));
//        bungAddBoardMap.put("writer", bungBoardDto.getBoardWriter());
//
//        System.out.println(result1);
//        System.out.println(bungBoardDto.getBoardWriter());
//
//        Long result2 = addWriterBungBoard(bungAddBoardMap);
//
//        if(result1 > 0 && result2 > 0) {
//            return result1;
//        } else {
//            new BoardException("게시글 작성이 실패하였습니다.");
//            return Long.parseLong(String.valueOf(0));
//        }
        return bungAddRepository.save(bungAddBoardEntity).getAddId();
    }

    // 메인 서비스 게시글 조회
    @Override
    public Result mainSelectList() {
        List<BungBoard> bungBoards = bungRepository.findByBoardStatus(BoardStatus.YES);
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
            System.out.println(jsonArray.getJSONObject(i).getString("imageName"));
            map.put("imageName", jsonArray.getJSONObject(i).getString("imageName"));
            map.put("objectKey", jsonArray.getJSONObject(i).getString("objectKey"));
            map.put("imageLevel", jsonArray.getJSONObject(i).getString("imageLevel"));
            mapList.add(map);
//            list.add(mapList);
        }
//        System.out.println(list);
        return new BungBoardDetailResponseDto(bungBoard, mapList);

    }

    @Override
    @Transactional
    public int increaseCount(Long bId) {
        return bungRepository.increaseCount(bId);
    }

    @Override
    public ConcurrentHashMap<String, String> getRecentBoardNo() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        long bId = 0L;
        try{
            bId = bungRepository.findFirstByOrderByBoardIdDesc().getBoardId()+1;
        } catch (NullPointerException e){
            map.put("bId", "1");
            return map;
        }
        map.put("bId", String.valueOf(bId));
        return map;
    }

    // 벙 신청 요청자 게시글 등록 메소드
    @Override
    @Transactional
    public Long addBungBoard(ConcurrentHashMap<String, String> bungAddBoardMap) {

        Long boardId = Long.parseLong(bungAddBoardMap.get("boardId"));

        BungBoard bungBoard = bungRepository.findBungBoardByBoardId(boardId);

        BungAddBoard bungAddBoard = BungAddBoard.createBungAddBoard(bungAddBoardMap, bungBoard);

        return bungAddRepository.save(bungAddBoard).getAddId();
    }

    // 주최자가 게시글 등록하면 bungAddBoard에 자동 작성되는 메소드 (삭제 예정)
//    @Override
//    @Transactional
//    public Long addWriterBungBoard(ConcurrentHashMap<String, String> bungAddBoardMap) {
//
////        Long boardId = Long.parseLong(bungAddBoardMap.get("boardId"));
//
//        Long bId = Long.parseLong(bungAddBoardMap.get("bId"));
//        System.out.println(bId);
//
//        BungBoard bungBoard = bungRepository.findBungBoardByBoardId(bId);
//
//        System.out.println(bungBoard);
//
//        BungAddBoard bungAddBoard = BungAddBoard.createWriterBungAddBoard(bungAddBoardMap, bungBoard);
//
//        return bungAddRepository.save(bungAddBoard).getAddId();
//    }

    @Override
    public Result addBungBoardsList() {
        List<BungAddBoard> bungAddBoard = bungAddRepository.findAll();
        List<BungAddBoardDto> collect = bungAddBoard.stream().map(BungAddBoardDto::new).collect(Collectors.toList());
        return new Result(collect);
    }

    @Override
    @Transactional
    public Long addBungBoardUpdate(Long addId, BungAddBoardDto bungAddBoardDto) {
        BungAddBoard bungAddBoard = bungAddRepository.findById(addId).orElseThrow(() ->new AddBoardException("해당하는 신청글이 존재하지 않습니다."));
        bungAddBoard.updateAddBoard(bungAddBoardDto);
        return bungAddBoard.getAddId();
    }

    @Override
    @Transactional
    public Long addBungBoardAccept(Long addId) {
        System.out.println("adddId : "+ addId);
        BungAddBoard addBungBoardAccept = bungAddRepository.findById(addId).orElseThrow(() ->new AddBoardException("해당하는 신청글이 존재하지 않습니다."));
//        System.out.println(addBungBoardAccept.getAddId());
        Long boardId = addBungBoardAccept.getAddId();
//        long boardId = addBungBoardAccept.getBungBoard().getBoardId();
        BungBoard bungBoard = bungRepository.findBungBoardByBoardId(boardId);
        System.out.println(boardId);
        Long fullGroup = bungBoard.getFullGroup();
        Long nowGroup = bungBoardNowGroup(boardId);

        if(nowGroup < fullGroup) {
            addBungBoardAccept.changeStatus();
            return addBungBoardAccept.getAddId();
        } else {
            new AddBoardException("모집인원이 초과하였습니다.");
            return null;
        }
//        return null;
    }

    @Override
    public BungAddBoardDto bungAddBoardSelect(Long addId) {
        BungAddBoard bungAddBoard = bungAddRepository.findById(addId).orElseThrow(() ->new AddBoardException("해당하는 신청글이 존재하지 않습니다."));
        BungAddBoardDto bungAddBoardDto = new BungAddBoardDto(bungAddBoard);
        return bungAddBoardDto;
    }

    @Override
    public Result bungRequestList(Long bId) {
        List<BungAddBoard> bungAddBoard = bungAddRepository.findBoardByBoardId(bId);
        List<BungAddBoardDto> collect = bungAddBoard.stream().map(BungAddBoardDto::new).collect(Collectors.toList());
        return new Result(collect);
    }

    @Override
    public Long addBungBoardDelete(Long addId) {
        BungAddBoard findBungAddBoard = bungAddRepository.findById(addId).orElseThrow(() ->new AddBoardException("해당하는 신청글이 존재하지 않습니다."));
        bungAddRepository.delete(findBungAddBoard);
        return findBungAddBoard.getAddId();
    }

    @Override
    @Transactional
    public Long bungBoardNowGroup(Long bId) {
        return bungAddRepository.bungBoardNowGroup(bId);
    }


}
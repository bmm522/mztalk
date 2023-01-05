package com.mztalk.bung.service.impl;

import com.mztalk.bung.domain.BoardStatus;
import com.mztalk.bung.domain.SearchKeyWord;
import com.mztalk.bung.domain.dto.BungAddBoardDto;
import com.mztalk.bung.domain.dto.BungBoardDto;
import com.mztalk.bung.domain.entity.BungAddBoard;
import com.mztalk.bung.domain.response.BungAddRequestDto;
import com.mztalk.bung.domain.entity.BungBoard;
import com.mztalk.bung.domain.entity.Result;
import com.mztalk.bung.domain.response.*;
import com.mztalk.bung.exception.AddBoardException;
import com.mztalk.bung.exception.BoardException;
import com.mztalk.bung.repository.BungAddBoardRepository;
import com.mztalk.bung.repository.BungBoardRepository;
import com.mztalk.bung.service.BungBoardService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
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
                boardWriterId(bungBoardDto.getBoardWriterId()).
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
                address(bungBoardDto.getAddress()).
                build();

        bungRepository.save(bungBoardEntity);

        Long boardId = bungBoardEntity.getBoardId();
        BungBoard bungBoard = bungRepository.findBungBoardByBoardId(boardId);
        BungAddBoard bungAddBoardEntity = BungAddBoard.builder().
                addPhone(null).
                addNickName(bungBoardDto.getBoardWriter()).
                boardStatus(BoardStatus.YES).
                bungBoard(bungBoard).
                build();

        return bungAddRepository.save(bungAddBoardEntity).getAddId();
    }

    @Override
    public Result<?> mainSelectList(int page) {
        // Create a Pageable object with a page size of 10 and the specified page number
        Pageable pageable = PageRequest.of(page - 1, 9);

        // Find the bung boards with a status of YES, using the Pageable object to paginate the results
        Page<BungBoard> bungBoards =bungRepository.findByBoardStatus(BoardStatus.YES, pageable);
        List<BungBoardResponseDto> bungBoardResponseDtoList = new ArrayList<>();
        for (BungBoard bungBoard : bungBoards) {
            System.out.println(bungBoard.getBoardId());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "text/html");
            ResponseEntity<String> response = new RestTemplate().exchange(
                    "http://localhost:8000/resource/main-image?bNo=" + bungBoard.getBoardId() + "&serviceName=bung",
                    HttpMethod.GET,
                    new HttpEntity<String>(headers),
                    String.class
            );
            JSONObject jsonObject = new JSONObject(response.getBody());
            JSONObject jsonData = jsonObject.getJSONObject("data");
            String imageUrl = jsonData.getString("imageUrl");
            String imageName = jsonData.getString("objectKey");


            long nowGroup = bungAddRepository.bungBoardNowGroup(bungBoard.getBoardId());
            System.out.println("nowGroup : " + nowGroup);
            bungBoardResponseDtoList.add(new BungBoardResponseDto(bungBoard, imageUrl, imageName, nowGroup));
        }
        return new Result(bungBoardResponseDtoList);
    }

    @Override
    public Result<?> mainSelectListOfMain(int page) {
        Pageable pageable = PageRequest.of(page - 1, 3);

        // Find the bung boards with a status of YES, using the Pageable object to paginate the results
        Page<BungBoard> bungBoards =bungRepository.findByBoardStatus(BoardStatus.YES, pageable);
        List<BungBoardResponseDto> bungBoardResponseDtoList = new ArrayList<>();
        for (BungBoard bungBoard : bungBoards) {
            System.out.println(bungBoard.getBoardId());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "text/html");
            ResponseEntity<String> response = new RestTemplate().exchange(
                    "http://localhost:8000/resource/main-image?bNo=" + bungBoard.getBoardId() + "&serviceName=bung",
                    HttpMethod.GET,
                    new HttpEntity<String>(headers),
                    String.class
            );
            JSONObject jsonObject = new JSONObject(response.getBody());
            JSONObject jsonData = jsonObject.getJSONObject("data");
            String imageUrl = jsonData.getString("imageUrl");
            String imageName = jsonData.getString("objectKey");


            long nowGroup = bungAddRepository.bungBoardNowGroup(bungBoard.getBoardId());
            System.out.println("nowGroup : " + nowGroup);
            bungBoardResponseDtoList.add(new BungBoardResponseDto(bungBoard, imageUrl, imageName, nowGroup));
        }
        return new Result(bungBoardResponseDtoList);
    }

    @Override
    public BungAddRequestDto addBungRefuse(Long addId) {
        BungAddBoard addBungBoardAccept = bungAddRepository.findById(addId).orElseThrow(() ->new AddBoardException("해당하는 신청글이 존재하지 않습니다."));
        long boardId = addBungBoardAccept.getBungBoard().getBoardId();
//        BungBoard bungBoard = bungRepository.findBungBoardByBoardId(boardId);
        Long result = bungAddRepository.addBungRefuse(addId, boardId);

        if(result > 0) {
            String message = "거절 요청이 완료되었습니다.";
            return new BungAddRequestDto(message);
        } else {
            String message = "거절 요청이 실패하였습니다.";
            return new BungAddRequestDto(message);
        }
    }


    // 메인 게시글 수정
    @Override
    @Transactional
    public Long mainBoardUpdate(Long bId, BungBoardDto bungBoardDto) {
        BungBoard saveBungBoard = bungRepository.findById(bId).orElseThrow(() -> new BoardException("해당 번호의 글이 존재하지 않습니다."));
        saveBungBoard.mainBoardUpdate(bungBoardDto);
        System.out.println(saveBungBoard.getAddress());
        return saveBungBoard.getBoardId();
    }

    @Override
    @Transactional
    public Long mainBoardDelete(Long bId) {
        BungBoard deleteBungBoard = bungRepository.findById(bId).orElseThrow(() -> new BoardException("해당 번호의 글이 존재하지 않습니다."));

        // 벙 모집 게시글 삭제 시 Status N으로 바뀌는 로직
        deleteBungBoard.changeStatus();

        // 벙 모집 게시글 삭제 시 addBoard에 있는 신청글 삭제 로직
        int result = bungAddRepository.deleteByBoardId(bId);
        if(result == 1 ) {
            return deleteBungBoard.getBoardId();
        } else {
            return 0L;
        }
    }

    @Override
    public BungBoardDetailResponseDto mainBoardSelect(Long bId) {
        BungBoard bungBoard = bungRepository.findById(bId).orElseThrow(() -> new BoardException("해당 번호의 글이 존재하지 않습니다."));
        List<ConcurrentHashMap<String, String>> mapList = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/html");
        ResponseEntity<String> response = new RestTemplate().exchange(
                "http://localhost:8000/resource/images?bNo=" + bId + "&serviceName=bung",
                HttpMethod.GET,
                new HttpEntity<String>(headers),
                String.class
        );

        JSONObject jsonObject = new JSONObject(response.getBody());
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        for (int i = 0; i < jsonArray.length(); i++) {
            ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
            map.put("imageUrl", jsonArray.getJSONObject(i).getString("imageUrl"));
            System.out.println(jsonArray.getJSONObject(i).getString("imageName"));
            map.put("imageName", jsonArray.getJSONObject(i).getString("imageName"));
            map.put("objectKey", jsonArray.getJSONObject(i).getString("objectKey"));
            map.put("imageLevel", jsonArray.getJSONObject(i).getString("imageLevel"));
            mapList.add(map);
        }
        return new BungBoardDetailResponseDto(bungBoard, mapList);

    }

    @Override
    @Transactional
    public int increaseCount(Long bId) {
        BungBoard bungBoard = bungRepository.findBungBoardByBoardId(bId);
        String boardWriter = bungBoard.getBoardWriter();
        return bungRepository.increaseCount(bId, boardWriter);
    }

    @Override
    public ConcurrentHashMap<String, String> getRecentBoardNo() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        long bId = 0L;
        try {
            bId = bungRepository.findFirstByOrderByBoardIdDesc().getBoardId() + 1;
        } catch (NullPointerException e) {
            map.put("bId", "1");
            return map;
        }
        map.put("bId", String.valueOf(bId));
        return map;
    }

    // 벙 신청 요청자 게시글 등록 메소드
    @Override
    @Transactional
    public BungAddRequestDto addBungBoard(ConcurrentHashMap<String, String> bungAddBoardMap) {
        Long boardId = Long.parseLong(bungAddBoardMap.get("boardId"));
        BungBoard bungBoard = bungRepository.findBungBoardByBoardId(boardId);
        String message = null;

        String addWriter = bungAddBoardMap.get("addNickName");
        // 벙 게시글 작성자 신청 방지 로직
//         String bungWriter = bungAddBoardMap.get("addNickName");
//       message = bungBoardWriterPrevention(boardId, bungWriter);
        if(duplicationPrevention(boardId, addWriter).equals(TimeLimit(boardId))) {
            message = TimeLimit(boardId);
            message = duplicationPrevention(boardId, addWriter);

            BungAddBoard bungAddBoard = BungAddBoard.createBungAddBoard(bungAddBoardMap, bungBoard);
            bungAddRepository.save(bungAddBoard).getAddId();
            return new BungAddRequestDto(message);
        } else if (duplicationPrevention(boardId, addWriter).equals("이미 신청한 게시글입니다.")) {
            // 마감제한 시 신청 방지 로직
            message = duplicationPrevention(boardId, addWriter);
            return new BungAddRequestDto(message);
        } else if (TimeLimit(boardId).equals("마감시간이 종료되었습니다.")) {
            // 벙 게시글 신청자 중복 신청 방지 로직
            message = TimeLimit(boardId);
            return new BungAddRequestDto(message);
        } else {
            return new BungAddRequestDto(message);
        }
    }

    private String TimeLimit(Long boardId) {
        LocalDate nowTime = LocalDate.now();
        Date bungBoardDeadlineDate = (Date) bungRepository.findBungBoardByDeadlineDate(boardId);
        Date nowTimeLast = Date.valueOf(nowTime);
        int result = nowTimeLast.compareTo(bungBoardDeadlineDate);
        if(result > 0) {
            return "마감시간이 종료되었습니다.";
        } else {
            return "신청이 완료되었습니다.";
        }
    }

    private String duplicationPrevention(Long boardId, String addWriter) {
        Optional<?> bungAddBoardWriter = bungAddRepository.findAddBoardByWriter(boardId, addWriter);
        if (bungAddBoardWriter.isPresent()) {
            return "이미 신청한 게시글입니다.";
        } else {
            return "신청이 완료되었습니다.";
        }
    }

//    private String bungBoardWriterPrevention(Long boardId, String bungWriter) {
//        String bungBoardWriter = bungRepository.findBungBoardWriter(boardId);
//
//    }


    @Override
    public Result addBungBoardsList(String boardWriter) {
//        BungListResponseDto bungListResponseDto = bungRepository.findBungBoardWriterAndBoardStatus(boardWriter, "NO");
        List<BungListResponseDto> bungListResponseDtoList = new ArrayList<>();

        List<BungBoardDto> bungBoardDtoList = bungRepository.findByBoardWriter(boardWriter);
//        bungBoardDtoList
        System.out.println(bungBoardDtoList.toString());
//        List<BungAddBoardDto> bungAddBoardList = bungRepository.findBungBoardWriterAndBoardStatus(boardWriter, BoardStatus.NO);
//        for (BungAddBoardDto board : bungAddBoardList) {
//            bungListResponseDtoList.add(new BungListResponseDto(board.getAddNickName(), board.getAddId()));
//        }
////        List<BungAddBoard> bungAddBoard = bungAddRepository.findAllWriter();
////        List<BungAddBoardDto> collect = bungAddBoard.stream().map(BungAddBoardDto::new).collect(Collectors.toList());
////        return new Result(collect);
//        return new Result<>(bungListResponseDtoList);
        return null;
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
    public BungAddRequestDto addBungBoardAccept(Long addId) {
        BungAddBoard addBungBoardAccept = bungAddRepository.findById(addId).orElseThrow(() ->new AddBoardException("해당하는 신청글이 존재하지 않습니다."));
        long boardId = addBungBoardAccept.getBungBoard().getBoardId();
        BungBoard bungBoard = bungRepository.findBungBoardByBoardId(boardId);
        Long fullGroup = bungBoard.getFullGroup();
        Long nowGroup = bungBoardNowGroup(boardId);

        if(nowGroup < fullGroup) {
            addBungBoardAccept.changeStatus();
            String message = "수락되었습니다.";
            return new BungAddRequestDto(message);
        } else {
            String message = "모집인원이 초과하였습니다.";
            return new BungAddRequestDto(message);
        }
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

    @Override
    public Result bungBoardSearch(SearchKeyWord searchKeyWord) {

        List<BungBoard> bungBoardList = bungRepository.search(searchKeyWord);
        List<BungBoardDto> collect = bungBoardList.stream().map(BungBoardDto::new).collect(Collectors.toList());

        return new Result(collect);
    }

    @Override
    @Transactional
    public Long bungAddBoardGroupDrop(Long bId, Long aId) {
        return bungAddRepository.bungAddBoardGroupDrop(bId, aId);
    }

    @Override
    public Long findBungBoard(Long bId) {
        return bungRepository.findBungBoard(bId);
    }

    @Override
    public Result<?> search(String[] categories, String type, String searchText, int page) {
        Pageable pageable = PageRequest.of(page - 1, 9);
        List<BungBoardResponseDto> bungBoardResponseDtoList = new ArrayList<>();
        Page<BungBoard> bungBoardList = bungRepository.getSearchList(categories, type, searchText, pageable);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/html");
        for(BungBoard bungBoard : bungBoardList){


            ResponseEntity<String> response = new RestTemplate().exchange(
                    "http://localhost:8000/resource/main-image?bNo=" + bungBoard.getBoardId() + "&serviceName=bung",
                    HttpMethod.GET,
                    new HttpEntity<String>(headers),
                    String.class
            );
            JSONObject jsonObject = new JSONObject(response.getBody());
            JSONObject jsonData = jsonObject.getJSONObject("data");
            String imageUrl = jsonData.getString("imageUrl");
            String imageName = jsonData.getString("objectKey");

            bungBoardResponseDtoList.add(new BungBoardResponseDto(bungBoard, imageUrl, imageName, bungAddRepository.bungBoardNowGroup(bungBoard.getBoardId())));
        }
        System.out.println(bungBoardResponseDtoList.get(0).getTitle());


        return new Result<>(bungBoardResponseDtoList);
    }

    @Override
    public Result<?> getAcceptList(String nickname) {
        List<BungAddBoard> bungAddBoardList = bungAddRepository.getAcceptList(nickname);
        List<AcceptResponseDto> AcceptResponseDtoList = new ArrayList<>();

        for (BungAddBoard bungAddBoard : bungAddBoardList){
            AcceptResponseDtoList.add(new AcceptResponseDto(bungAddBoard));
        }
        return new Result<>(AcceptResponseDtoList);
    }



}
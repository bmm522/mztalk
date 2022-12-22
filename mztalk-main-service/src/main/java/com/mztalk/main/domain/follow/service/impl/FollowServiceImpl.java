package com.mztalk.main.domain.follow.service.impl;



import com.mztalk.main.domain.follow.dto.FollowDto;
import com.mztalk.main.domain.follow.dto.FollowListResponseDto;
import com.mztalk.main.domain.follow.entity.Follow;
import com.mztalk.main.domain.follow.repository.FollowRepository;
import com.mztalk.main.domain.follow.service.FollowService;
import com.mztalk.main.handler.exception.ExceptionCode;
import com.mztalk.main.handler.exception.FollowException;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.*;



@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;

   private final EntityManager em;


    @Override
    @Transactional
    public void follow(Long toUserNo, Long fromUserNo) {
        try {
            followRepository.mFollow(fromUserNo, toUserNo);
        } catch(Exception e){
            throw new FollowException(ExceptionCode.ALREADY_EXIST_FOLLOW);
        }

    }

    @Override
    @Transactional
    public void unFollow(Long toUserId, Long fromUserId) {
        followRepository.mUnFollow(fromUserId, toUserId);
    }




    @Override
    @Transactional
    public List<FollowListResponseDto> followList(Long own, Long userNo) {
        System.out.println("own : " + own);
        List<Follow> followList = followRepository.getListByFromUserId(own);
        System.out.println("길이 : " + followList.size());
        List<FollowListResponseDto> followDtoList = new ArrayList<>();

        for(Follow follow : followList){

//            HttpHeaders headersImg = new HttpHeaders();
//            headersImg.add("Content-type", "text/html");
//
//            ResponseEntity<String> responseImg = new RestTemplate().exchange(
//                "http://localhost:8000/resource/main-image?bNo="+follow.getFromUserId()+"&serviceName=story",    //첫번째: url
//               HttpMethod.GET,
//                new HttpEntity<String>(headersImg),     //바디, 헤더 다 담기 가능/엔티티
//                String.class
//            );
//
//            JSONObject jsonObject = new JSONObject(responseImg.getBody());
//            JSONObject jsonData = jsonObject.getJSONObject("data");
//            String imageUrl = jsonData.getString("imageUrl");
//            String imageName = jsonData.getString("objectKey");

            //페이지주인이름
            HttpHeaders headersName = new HttpHeaders();
            headersName.add("Content-type", "text/html");

            ResponseEntity<String> responseName = new RestTemplate().exchange(
                    "http://localhost:8000/login/user-info/"+follow.getToUserId(),
                    HttpMethod.GET,
                    new HttpEntity<String>(headersName),
                    String.class
            );


            JSONObject ownName = new JSONObject(responseName.getBody());
            String nickname = ownName.getString("nickname");








            followDtoList.add(new FollowListResponseDto(follow, nickname, "imageUrl", "nam"));

        }


        return followDtoList;


























        //개인별로 사진..?
//        HttpHeaders headersImg = new HttpHeaders();
//        headersImg.add("Content-type", "text/html");
//
//        ResponseEntity<String> responseImg = new RestTemplate().exchange(
//                "http://localhost:8000/resource/main-image?bNo="+userNo+"&serviceName=story",    //첫번째: url
//                HttpMethod.GET,
//                new HttpEntity<String>(headersImg),     //바디, 헤더 다 담기 가능/엔티티
//                String.class
//        );
//
//        JSONObject jsonObject = new JSONObject(responseImg.getBody());
//        JSONArray jsonArray = jsonObject.getJSONArray("data");
//        JSONObject obj = jsonArray.getJSONObject(0);
//        String profileImageUrl = obj.getString("imageUrl");









        //쿼리준비
//        StringBuffer sb = new StringBuffer();
//        sb.append("select f1.followId, f1.fromUserId, f1.toUserId, f1.profileImageUrl, ");
//        sb.append("if(f2.fromUserId is null, false, true) \"matpal\" ");
//        sb.append("from follow f1 left outer join follow f2 ");
//        sb.append("on f1.fromUserId=f2.toUserId and f1.toUserId= f2.fromUserId ");
//        sb.append("order by f1.followId; ");


        // 1.물음표 principalId
        // 2.물음표 principalId
        // 3.물음표 pageUserId

//        select f1.followId, f1.fromUserId, f1.toUserId, f1.profileImageUrl,
//        if(f2.fromUserId is null, false, true) "matpal"
//        from follow f1 left outer join follow f2
//        on f1.fromUserId=f2.toUserId and f1.toUserId= f2.fromUserId
//        order by f1.followId;


        // 쿼리 완성
 //       Query query = em.createNativeQuery(sb.toString());
//                .setParameter(1, principalId)
//                .setParameter(2, principalId)
//                .setParameter(3, pageUserId);

        // 쿼리 실행 (qlrm 라이브러리 필요 = DTO에 DB결과를 매핑하기 위해서)
//        JpaResultMapper result = new JpaResultMapper();
//        List<FollowDto> followDtos =  result.list(query, FollowDto.class);
//
//        return followDtos;
    }





//    @Override
//    public Boolean addFollow(Long toUserId, Long fromUserId) {
//
//        if(Objects.equals(toUserId, fromUserId)){
//            throw new FollowException(ExceptionCode.SAME_ACCOUNT);
//        }
//
//        Board toUser = boardRepository.findByUserId(toUserId)
//                .orElseThrow(()-> new CustomApiException(ExceptionCode.NOT_FOUND_ACCOUNT));
//
//        Board fromUser = boardRepository.findByUserId(fromUserId)
//                .orElseThrow(() -> new CustomApiException(ExceptionCode.NOT_FOUND_ACCOUNT));
//
//        Optional<Follow> relation = getFollowRelation(toUser.getId(), fromUser.getId());
//        if(relation.isPresent()) {
//            throw new FollowException(ExceptionCode.ALREADY_EXIST_FOLLOW);
//        }
//        followRepository.save(new Follow(toUserId,  fromUserId));
//
//        return true;
//    }
//
//    private Optional<Follow> getFollowRelation(Long toUserId, Long fromUserId) {
//
//        return followRepository.findByToUserIdAndFromUserId(toUserId, fromUserId);
//    }
}

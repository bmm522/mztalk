package com.mztalk.story.service.impl;



import com.mztalk.story.domain.follow.dto.*;
import com.mztalk.story.domain.follow.Follow;
import com.mztalk.story.repository.FollowRepository;
import com.mztalk.story.service.FollowService;
import com.mztalk.story.handler.exception.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FollowServiceImpl implements FollowService {
    private final FollowRepository followRepository;
    //팔로우
    @Override
    @Transactional
    public void follow(Long toUserId, Long fromUserId) {
        try {
           followRepository.mFollow(fromUserId, toUserId);
        } catch (Exception e) {
            throw new CustomApiException("이미 팔로우 하셨습니다.");
        }
    }

    //언팔로우
    @Override
    @Transactional
    public void unFollow(Long toUserId, Long fromUserId) {
        followRepository.mUnFollow(fromUserId, toUserId);
    }

    //팔로우=1/ 언팔=0
    @Override
    @Transactional
    public Long followStatus(Long fromUserId, Long toUserId) {return followRepository.followStatus(fromUserId, toUserId);}



    //팔로워리스트
    @Override
    @Transactional
    public List<FollowListResponseDto> followList(Long toUserId) {

        List<Follow> followList = followRepository.getListByToUserId(toUserId);
        List<FollowListResponseDto> followDtoList = new ArrayList<>();
        String imageUrl = "";
        String imageName = "";

        for (Follow follow : followList) {

            //유저 정보
            HttpHeaders headersName = new HttpHeaders();
            headersName.add("Content-type", "text/html");
            ResponseEntity<String> responseName = new RestTemplate().exchange(
                    "http://localhost:8000/login/user-info/" + follow.getFromUserId(),
                    HttpMethod.GET,
                    new HttpEntity<String>(headersName),
                    String.class
            );
            JSONObject ownName = new JSONObject(responseName.getBody());
            String nickname = ownName.getString("nickname");


            //사진이랑 사진이름
            HttpHeaders headersImg = new HttpHeaders();
            headersImg.add("Content-type", "text/html");

            try {
            ResponseEntity<String> responseImg = new RestTemplate().exchange(
                    "http://localhost:8000/resource/main-image?bNo=" + follow.getFromUserId() + "&serviceName=story",
                    HttpMethod.GET,
                    new HttpEntity<String>(headersImg),
                    String.class
            );
            JSONObject profileImage = new JSONObject(responseImg.getBody());
            JSONObject profileData = profileImage.getJSONObject("data");
            imageUrl = profileData.getString("imageUrl");
            imageName = profileData.getString("objectKey");

            } catch (Exception e){
                imageUrl = "https://mztalk-resource-server.s3.ap-northeast-2.amazonaws.com/7276284f-daed-4b0d-9ca3-7a7bb1930138-profile.png";
                imageName = "기본이미지";
            }
            followDtoList.add(new FollowListResponseDto(follow, nickname, imageUrl, "기본이미지", follow.getFollowStatus()));
        }

        return followDtoList;
    }

    //팔로잉리스트
    @Override
    @Transactional
    public List<FollowingListResponseDto> followingList(Long fromUserId) {

        List<Follow> followList = followRepository.getListByFromUserId(fromUserId);
        List<FollowingListResponseDto> followDtoList = new ArrayList<>();

        String imageUrl = "";
        String imageName = "";
        for (Follow follow : followList) {
            //유저 정보
            HttpHeaders headersName = new HttpHeaders();
            headersName.add("Content-type", "text/html");

            ResponseEntity<String> responseName = new RestTemplate().exchange(
                    "http://localhost:8000/login/user-info/" + follow.getToUserId(),
                    HttpMethod.GET,
                    new HttpEntity<String>(headersName),
                    String.class
            );
            JSONObject ownName = new JSONObject(responseName.getBody());
            String nickname = ownName.getString("nickname");

            //사진이랑 사진이름
            HttpHeaders headersImg = new HttpHeaders();
            headersImg.add("Content-type", "text/html");
            try{
                ResponseEntity<String> responseImg = new RestTemplate().exchange(
                        "http://localhost:8000/resource/main-image?bNo=" + follow.getToUserId() + "&serviceName=story",
                        HttpMethod.GET,
                        new HttpEntity<String>(headersImg),
                        String.class
                );
                JSONObject profileImage = new JSONObject(responseImg.getBody());
                JSONObject profileData = profileImage.getJSONObject("data");
                imageUrl = profileData.getString("imageUrl");
                imageName = profileData.getString("objectKey");
            } catch(Exception e) {
                imageUrl = "https://mztalk-resource-server.s3.ap-northeast-2.amazonaws.com/7276284f-daed-4b0d-9ca3-7a7bb1930138-profile.png";
                imageName ="기본이미지";
            }
            followDtoList.add(new FollowingListResponseDto(follow, nickname, imageUrl, imageName, follow.getFollowStatus()));
        }
        return followDtoList;
    }




    //맞팔 리스트
    @Override
    public List<MatpalListResponseDto> matpalList(Long fromUserId) {
        List<MatpalGroup> matpalListResponseDtoList = followRepository.getListByMatpalListFromUserId(fromUserId);
        List<MatpalListResponseDto> matpalListResponseDtos = new ArrayList<>();

        for(MatpalGroup matpalGroup : matpalListResponseDtoList){
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "text/html");
            String imageUrl = "";
            String imageName = "";
            try{
                ResponseEntity<String> response = new RestTemplate().exchange(
                        "http://localhost:8000/resource/main-image?bNo=" + matpalGroup.getToUserId() + "&serviceName=story",
                        HttpMethod.GET,
                        new HttpEntity<String>(headers),
                        String.class
                );
                JSONObject profileImage = new JSONObject(response.getBody());
                JSONObject profileData = profileImage.getJSONObject("data");
                imageUrl = profileData.getString("imageUrl");
                imageName = profileData.getString("objectKey");
            } catch (Exception e){
                imageUrl = "https://mztalk-resource-server.s3.ap-northeast-2.amazonaws.com/7276284f-daed-4b0d-9ca3-7a7bb1930138-profile.png";
                imageName = "기본프로필사진";
            }
            matpalListResponseDtos.add(new MatpalListResponseDto(matpalGroup.getFromUserId(), matpalGroup.getToUserId(), imageUrl, matpalGroup.getFollowStatus(), matpalGroup.getMatpal()));
        }
        return matpalListResponseDtos;
    }

}






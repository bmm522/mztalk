package com.mztalk.main.domain.follow.service.impl;



import com.mztalk.main.domain.follow.dto.*;
import com.mztalk.main.domain.follow.entity.Follow;
import com.mztalk.main.domain.follow.repository.FollowCustomRepository;
import com.mztalk.main.domain.follow.repository.FollowRepository;
import com.mztalk.main.domain.follow.service.FollowService;
import com.mztalk.main.domain.profile.entity.Profile;
import com.mztalk.main.domain.profile.repository.ProfileCustomRepository;
import com.mztalk.main.handler.exception.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;


import java.util.*;



@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;

    private final FollowCustomRepository followCustomRepository;

    private final ProfileCustomRepository profileCustomRepository;

    private final EntityManager em;


    @Override
    @Transactional
    public void follow(Long toUserId, Long fromUserId) {
        try {
            followRepository.mFollow(fromUserId, toUserId);
        } catch (Exception e) {

            throw new CustomApiException("이미 팔로우 하셨습니다.");
        }
    }


    @Override
    @Transactional
    public void unFollow(Long toUserId, Long fromUserId) {
        followRepository.mUnFollow(fromUserId, toUserId);
    }


    @Override
    @Transactional
    public List<FollowListResponseDto> followList(Long toUserId) {
        //own을 팔로우 하고 있는 모든 사람(login한 사람한테 보여줘야하니)

        System.out.println("own : " + toUserId);
        List<Follow> followList = followRepository.getListByToUserId(toUserId);
        System.out.println("followList" + followList);
        System.out.println("길이 : " + followList.size());
        List<FollowListResponseDto> followDtoList = new ArrayList<>();

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
            Optional<Profile> profile = profileCustomRepository.findByUserImage(follow.getFromUserId());
            //Optional<Profile> profile = profileCustomRepository.findByUserStatus(toUserId);
            System.out.println("팔로워 사진" + profile);

            if (profile.isPresent()) {

                ResponseEntity<String> responseImg = new RestTemplate().exchange(
                        "http://localhost:8000/resource/main-image?bNo=" + follow.getFromUserId() + "&serviceName=story",
                        HttpMethod.GET,
                        new HttpEntity<String>(headersImg),
                        String.class
                );

                JSONObject profileImage = new JSONObject(responseImg.getBody());
                JSONObject profileData = profileImage.getJSONObject("data");
                String imageUrl = profileData.getString("imageUrl");
                String imageName = profileData.getString("objectKey");

                System.out.println("여기 오니?!");
                followDtoList.add(new FollowListResponseDto(follow, nickname, imageUrl, imageName, follow.getFollowStatus()));


            } else {

                String personalUrl = "https://mztalk-resource-server.s3.ap-northeast-2.amazonaws.com/7276284f-daed-4b0d-9ca3-7a7bb1930138-profile.png";
                followDtoList.add(new FollowListResponseDto(follow, nickname, personalUrl, "기본이미지", follow.getFollowStatus()));


            }


        }
        System.out.println("followDtoList" + followDtoList);


        return followDtoList;


    }

    //팔로잉리스트
    @Override
    @Transactional
    public List<FollowingListResponseDto> followingList(Long fromUserId) {

        System.out.println("own : " + fromUserId);
        List<Follow> followList = followRepository.getListByFromUserId(fromUserId);
        System.out.println("길이 : " + followList.size());
        List<FollowingListResponseDto> followDtoList = new ArrayList<>();

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
            Optional<Profile> profile = profileCustomRepository.findByToUserImage(follow.getToUserId());

            if (profile.isPresent()) {

                ResponseEntity<String> responseImg = new RestTemplate().exchange(
                        "http://localhost:8000/resource/main-image?bNo=" + follow.getToUserId() + "&serviceName=story",
                        HttpMethod.GET,
                        new HttpEntity<String>(headersImg),
                        String.class
                );

                JSONObject profileImage = new JSONObject(responseImg.getBody());
                JSONObject profileData = profileImage.getJSONObject("data");
                String imageUrl = profileData.getString("imageUrl");
                String imageName = profileData.getString("objectKey");

                //System.out.println("여기 오니?!");
                followDtoList.add(new FollowingListResponseDto(follow, nickname, imageUrl, imageName, follow.getFollowStatus()));

            } else {

                String personalUrl = "https://mztalk-resource-server.s3.ap-northeast-2.amazonaws.com/7276284f-daed-4b0d-9ca3-7a7bb1930138-profile.png";
                followDtoList.add(new FollowingListResponseDto(follow, nickname, personalUrl, "기본이미지", follow.getFollowStatus()));

            }

        }
        System.out.println("followDtoList" + followDtoList);

        return followDtoList;

    }

    @Override
    @Transactional
    public Long followStatus(Long fromUserId, Long toUserId) {

        Long followDto = followRepository.followStatus(fromUserId, toUserId);

        return followDto;
    }


    //맞팔 리스트
    @Override
    public List<MatpalGroup> matpalList(Long fromUserId) {

        List<MatpalGroup> matpalListResponseDtoList = followRepository.getListByMatpalListFromUserId(fromUserId);

            for (MatpalGroup m : matpalListResponseDtoList) {
                System.out.println("없니?" + m.getToUserId());

            }
            return matpalListResponseDtoList;
    }


}






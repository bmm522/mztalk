package com.mztalk.main.domain.profile.service.impl;

import com.mztalk.main.domain.board.repository.BoardRepository;
import com.mztalk.main.domain.follow.repository.FollowRepository;
import com.mztalk.main.domain.profile.dto.ProfileDto;
import com.mztalk.main.domain.profile.dto.ProfileImageResponseDto;
import com.mztalk.main.domain.profile.dto.ProfileResponseDto;
import com.mztalk.main.domain.profile.entity.Profile;
import com.mztalk.main.domain.profile.repository.ProfileRepository;
import com.mztalk.main.domain.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProfileServiceImpl implements ProfileService {
    private final FollowRepository followRepository;
    private final BoardRepository boardRepository;

    //개인 프로필 사진
    @Override
    @Transactional
    public ProfileImageResponseDto profileImg(long own) {
        ProfileImageResponseDto profileImageResponseDto = new ProfileImageResponseDto();

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

            return ProfileImageResponseDto.builder()
                    .postImageUrl(imageUrlOwn)
                    .profileImageName(imageName)
                    .build();

        } catch (Exception e) {

            String personalUrl = "https://mztalk-resource-server.s3.ap-northeast-2.amazonaws.com/7276284f-daed-4b0d-9ca3-7a7bb1930138-profile.png";
            String imageName = "기본프로필사진";
            return profileImageResponseDto.builder()
                    .profileImageName(imageName)
                    .postImageUrl(personalUrl)
                    .build();
        }


    }

    //개인 프로필 이름
    @Override
    @Transactional(readOnly = true)
    public ProfileResponseDto profileName(long own) {

        HttpHeaders headerName = new HttpHeaders();
        headerName.add("Content-type", "text/html");

        //유저의이름
        HttpHeaders headersNames = new HttpHeaders();
        headersNames.add("Content-type", "text/html");

        ResponseEntity<String> responseName = new RestTemplate().exchange(
                "http://localhost:8000/login/user-info/" + own,
                HttpMethod.GET,
                new HttpEntity<String>(headerName),
                String.class
        );

        JSONObject ownName = new JSONObject(responseName.getBody());
        String nickname = ownName.getString("nickname");

        return ProfileResponseDto.builder()
                .nickname(nickname)
                .build();
    }

    //게시물 갯수 보여주기
    @Override
    @Transactional
    public Long boardCount(long own) {
        return boardRepository.countByOwn(own);
    }

    //팔로워 카운트
    @Override
    @Transactional
    public Long followerCount(long own) {
        return followRepository.countByToUserId(own);
    }

    //팔로잉 카운트
    @Override
    @Transactional
    public Long followingCount(long own) {
        return followRepository.countByFromUserId(own);
    }

    //이미지 바꾸기
    @Override
    @Transactional
    public ProfileResponseDto changeProfile(long own, ProfileDto profileDto) {

        HttpHeaders headerImage = new HttpHeaders();
        headerImage.add("Content-type", "text/html");

        ResponseEntity<String> responseProImg = new RestTemplate().exchange(
                "http://localhost:8000/resource/main-image?bNo=" + own + "&serviceName=story",
                HttpMethod.GET,
                new HttpEntity<String>(headerImage),
                String.class
        );
        JSONObject profileImg = new JSONObject(responseProImg.getBody());
        JSONObject profileData = profileImg.getJSONObject("data");
        String objectKey = profileData.getString("objectKey");
        String imageUrl = profileData.getString("imageUrl");

        return ProfileResponseDto.builder()
                .postImageUrl(imageUrl)
                .profileImageName(objectKey)
                .build();
    }
}


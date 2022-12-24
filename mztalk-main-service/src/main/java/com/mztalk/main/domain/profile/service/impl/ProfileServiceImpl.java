package com.mztalk.main.domain.profile.service.impl;


import com.mztalk.main.domain.follow.repository.FollowRepository;
import com.mztalk.main.domain.profile.dto.ProfileDto;
import com.mztalk.main.domain.profile.dto.ProfileResponseDto;
import com.mztalk.main.domain.profile.entity.Profile;
import com.mztalk.main.domain.profile.repository.ProfileCustomRepository;
import com.mztalk.main.domain.profile.repository.ProfileRepository;
import com.mztalk.main.domain.profile.service.ProfileService;
import com.mztalk.main.domain.board.repository.BoardRepository;
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

import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProfileServiceImpl implements ProfileService {
    private final FollowRepository followRepository;
    private final ProfileRepository profileRepository;

    private final ProfileCustomRepository profileCustomRepository;

    private final BoardRepository boardRepository;


    //개인 프로필 사진
    @Override
    @Transactional(readOnly = true)
    public Optional<Profile> ProfileImg(long own) {

        HttpHeaders headersImg = new HttpHeaders();
        headersImg.add("Content-type", "text/html");
        Optional<Profile> profile = profileCustomRepository.findByUserStatus(own);

        System.out.println("개인페이지 사진" +profile);
        if(profile.isPresent()){


            ResponseEntity<String> responseImg = new RestTemplate().exchange(
                    "http://localhost:8000/resource/main-image?bNo=" + own + "&serviceName=story",    //첫번째: url
                    HttpMethod.GET,
                    new HttpEntity<String>(headersImg),     //바디, 헤더 다 담기 가능/엔티티
                    String.class
            );

            JSONObject profileImage = new JSONObject(responseImg.getBody());
            JSONObject profileData = profileImage.getJSONObject("data");
            String imageUrl = profileData.getString("imageUrl");
            String imageName = profileData.getString("objectKey");

            ProfileDto.builder()
                    .profileUrl(imageUrl)
                    .profileImageName(imageName)
                    .build();

             Profile.builder()
                    .postImageUrl(profile.get().getPostImageUrl())
                    .build();

            return profile;
        }else {
            String personalUrl = "https://mztalk-resource-server.s3.ap-northeast-2.amazonaws.com/7276284f-daed-4b0d-9ca3-7a7bb1930138-profile.png";
            System.out.println("여기 실행?!!!!");

            return Optional.ofNullable(Profile.builder()
                    .postImageUrl(personalUrl)
                    .profileImageName("기본이미지")
                    .build());
        }




    }



    //개인 프로필 이름
    @Override
    @Transactional(readOnly = true)
    public Profile ProfileName(long own) {

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

        return Profile.builder()
                .nickname(nickname)
                .build();
    }

    //게시물 갯수 보여주기
    @Override
    @Transactional
    public Profile BoardCount(long own) {

        long count = boardRepository.countByOwn(own);

        return Profile.builder()
                .boardCount(count)
                .build();
    }

    @Override
    public Profile followerCount(long own) {

        long count = followRepository.countByToUserId(own);

        return Profile.builder()
                    .followerCount(count)
                    .build();
    }

    @Override
    public Profile followingCount(long own) {

        long count = followRepository.countByFromUserId(own);

        return Profile.builder()
                .followingCount(count)
                .build();
    }

    //이미지바꾸기
    @Override
    @Transactional
    public ProfileResponseDto changeProfile(long own, ProfileDto profileDto) {

        HttpHeaders headerImage = new HttpHeaders();
        headerImage.add("Content-type", "text/html");

        ResponseEntity<String> responseproImg = new RestTemplate().exchange(
                "http://localhost:8000/resource/main-image?bNo="+own+"&serviceName=story",    //첫번째: url
                HttpMethod.GET,
                new HttpEntity<String>(headerImage),     //바디, 헤더 다 담기 가능/엔티티
                String.class
        );

        JSONObject profileImg = new JSONObject(responseproImg.getBody());
            //String objectKey = profileImg.getString("objectKey");
         System.out.println("프로필이미지 관련 : "+ profileImg);
         // System.out.println(objectKey);
        //profileImg.put("imageUrl", profileImg.getString("imageUrl"));

        JSONObject profileData = profileImg.getJSONObject("data");

        String imageUrl = profileData.getString("imageUrl");
        String imageName = profileData.getString("objectKey");

        //System.out.println(imageUrl);
        //System.out.println(imageName);




        //유저의이름
        HttpHeaders headersNames = new HttpHeaders();
        headersNames.add("Content-type", "text/html");

        ResponseEntity<String> responseName = new RestTemplate().exchange(
                "http://localhost:8000/login/user-info/" + own,
                HttpMethod.GET,
                new HttpEntity<String>(headerImage),
                String.class
        );

        JSONObject ownName = new JSONObject(responseName.getBody());
        String nickname = ownName.getString("nickname");

        Profile profile = profileRepository.save(profileDto.toEntity(profileData, ownName));

        return new ProfileResponseDto(profile);

    }




    //String profileUrl = profileRepository.save(imageUrl);

//        return ProfileDto.builder()
//                .profileUrl(imageUrl)
//                .profileImageName(imageName)
//                .nickname(nickname)
//                .build();




//    //프로필 이미지 변경
//    @Override
//    public ProfileVo getProfileImage(long own) {
//        HttpHeaders headersProfileImg = new HttpHeaders();
//        headersProfileImg.add("Content-type", "text/html");
//
//
//        ResponseEntity<String> responseProfileImg = new RestTemplate().exchange(
//                "http://localhost:8000/resource/main-image",
//                HttpMethod.GET,
//                new HttpEntity<String>(headersProfileImg),
//                String.class
//        );
//
//        JSONObject jsonObject = new JSONObject(responseProfileImg.getBody());
//        JSONArray jsonArray = jsonObject.getJSONArray("data");
//        JSONObject obj = jsonArray.getJSONObject(0);
//        String imageUrl = obj.getString("imageUrl");
//
//
//        System.out.println(imageUrl);
//
//
//
//
//
//        return ProfileVo.builder()
//                .profileUrl(imageUrl)
//                .nickname(nickname)
//                .build();
//    }
























//    private String asString(String data,String dataname) {
//        try{
//            JsonParser parser = new JsonParser();
//            JsonElement element = parser.parse(data);
//            return element.getAsJsonObject().get(dataname).getAsString();
//        } catch(Exception e) {
//            log.error("not JsonObject");
//        }
//        return "not JsonObject";
//    }
    }


// for(int i = 0 ; i < jsonArray.length(); i++){
//        JSONObject obj = jsonArray.getJSONObject(0);
//        String imageName = obj.getString("imageName");
//        String imageUrl = obj.getString("imageUrl");
//
//        System.out.println("imageName : " + imageName);
//        System.out.println("imageUrl : " + imageUrl);
//        }

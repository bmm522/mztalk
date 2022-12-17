package com.mztalk.main.domain.follow.service.impl;


import com.mztalk.main.domain.follow.service.ProfileService;
import com.mztalk.main.domain.vo.ProfileVo;
import com.mztalk.main.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
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

    private final BoardRepository boardRepository;

    @Override
    public ProfileVo getProfile(long own) {
        //프로필사진
        HttpHeaders headersImg = new HttpHeaders();
        headersImg.add("Content-type", "text/html");


        ResponseEntity<String> responseImg = new RestTemplate().exchange(
          "http://localhost:8000/resource/sub-image?bNo="+own+"&serviceName=story",    //첫번째: url
                HttpMethod.GET,
                new HttpEntity<String>(headersImg),     //바디, 헤더 다 담기 가능/엔티티
                String.class
        );

        JSONObject jsonObject = new JSONObject(responseImg.getBody());
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        JSONObject obj = jsonArray.getJSONObject(0);
        String imageUrl = obj.getString("imageUrl");




        //페이지주인이름
        HttpHeaders headersName = new HttpHeaders();
        headersName.add("Content-type", "text/html");

        ResponseEntity<String> responseName = new RestTemplate().exchange(
                "http://localhost:8000/login/user-info/"+own,
                HttpMethod.GET,
                new HttpEntity<String>(headersImg),
                String.class
        );

//        System.out.println("response2 : " + response2.getBody());
        JSONObject ownName = new JSONObject(responseName.getBody());
        String nickname = ownName.getString("nickname");

//        System.out.println("nickname : " + nickname);


        //유저 게시물
        long count = boardRepository.countByOwn(own);

        return ProfileVo.builder()
                .profileUrl(imageUrl)
                .nickname(nickname)
                .boardCount(count)
                .followerCount(1L)   //임시 더미데이터
                .followingCount(2L)  //임시 더미데이터
                .build();
    }


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

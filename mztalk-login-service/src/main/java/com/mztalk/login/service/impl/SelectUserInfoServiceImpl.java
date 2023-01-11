package com.mztalk.login.service.impl;

import com.mztalk.login.domain.dto.response.MaliciousUserResponseDto;
import com.mztalk.login.domain.dto.Result;
import com.mztalk.login.domain.dto.UserInfoDto;
import com.mztalk.login.domain.dto.response.SearchUsernameResponseDto;
import com.mztalk.login.domain.entity.User;
import com.mztalk.login.exception.UserNoNotFoundException;
import com.mztalk.login.repository.UserRepository;
import com.mztalk.login.service.SelectUserInfoService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class SelectUserInfoServiceImpl implements SelectUserInfoService {

    private final UserRepository userRepository;
    @Override
    public SearchUsernameResponseDto searchUsername(String email) {

        User user = userRepository.findByEmail(email);

        if(user == null){
            return new SearchUsernameResponseDto("notExist");
        }

        return new SearchUsernameResponseDto(user.getUsername());
    }


    @Override
    public UserInfoDto getUserInfoByUserNo(String id) {
        User user = userRepository.findById(Long.parseLong(id))
                .orElseThrow(()->new UserNoNotFoundException("Not Found User No"));

        String imageUrl = "";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "text/html");

            ResponseEntity<String> response = new RestTemplate().exchange(
                    "http://localhost:8000/resource/main-image?bNo=" + user.getId() + "&serviceName=story",
                    HttpMethod.GET,
                    new HttpEntity<String>(headers),
                    String.class
            );
            JSONObject jsonObject = new JSONObject(response.getBody());
            JSONObject jsonData = jsonObject.getJSONObject("data");
           imageUrl =  jsonData.getString("imageUrl");
        } catch (Exception e){
            imageUrl = "https://mztalk-resource-server.s3.ap-northeast-2.amazonaws.com/7276284f-daed-4b0d-9ca3-7a7bb1930138-profile.png";
        }

        return user.toUserInfoDto(imageUrl);
    }


    @Override
    public UserInfoDto getUserInfoByNickname(String nickname) {
        User user = null;
        try {
            user = userRepository.findByNickname(nickname);
        } catch (NoResultException e){
            throw new UserNoNotFoundException("Not Found User Nickname");
        }
        return user.toUserInfoDto();
    }

    @Override
    public Result<?> getMaliciousUser() {
        List<MaliciousUserResponseDto> maliciousUserResponseDtoList = new ArrayList<>();

        for(User user : userRepository.getMaliciousUser()){
            maliciousUserResponseDtoList.add(new MaliciousUserResponseDto(user));
        }

        return new Result<>(maliciousUserResponseDtoList);
    }




}

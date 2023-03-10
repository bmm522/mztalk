package com.mztalk.loginservice.user.application.login;

import com.mztalk.loginservice.user.application.login.dto.response.ServiceUserInfoResponseDto;
import com.mztalk.loginservice.user.application.login.dto.response.ServiceSearchUsernameResponseDto;
import com.mztalk.loginservice.user.application.login.dto.response.ServiceUserInfoResponseDtos;
import com.mztalk.loginservice.user.application.login.mapper.EntityToServiceDtoMapper;
import com.mztalk.loginservice.user.repository.entity.User;
import com.mztalk.loginservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SelectUserInfoServiceImpl implements SelectUserInfoService {

    private final UserRepository userRepository;

    private final EntityToServiceDtoMapper mapper = EntityToServiceDtoMapper.getInstance();
    @Override
    public ServiceSearchUsernameResponseDto searchUsername(String email) {

        User user = userRepository.findByEmail(email);

        if(user == null){
            return new ServiceSearchUsernameResponseDto("notExist");
        }

        return new ServiceSearchUsernameResponseDto(user.getUsername());
    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public ServiceUserInfoResponseDto getUserInfoByUserNo(String id) {
        User user =  getUserById(Long.parseLong(id));

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

        return mapper.toUserInfoDto(imageUrl, user);
    }


    @Override
    public ServiceUserInfoResponseDtos getMaliciousUser() {

        List<ServiceUserInfoResponseDto> dtos = userRepository.getMaliciousUser().stream()
                .map((user) -> mapper.toUserInfoDto(user))
                .collect(Collectors.toList());

        return ServiceUserInfoResponseDtos.builder().users(dtos).build();
    }


    private User getUserById(Long id) {
        Optional<User> userOptional = Optional.of(userRepository.findById(id))
                .orElseThrow(()-> new RuntimeException("번호에 해당하는 유저가 없습니다."));

        return userOptional.get();
    }


}

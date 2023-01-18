package com.mztalk.login.oauth;

import com.mztalk.login.domain.dto.request.FirstSocialRequestDto;
import com.mztalk.login.domain.dto.response.JwtFirstSocialResponseDto;
import com.mztalk.login.domain.dto.response.JwtResponseDto;
import com.mztalk.login.domain.entity.User;
import com.mztalk.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.mztalk.login.service.JwtTokenFactory.getJwtTokenFactoryInstance;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateNicknameSocialLoginService {

    private final UserRepository userRepository;
    public JwtFirstSocialResponseDto setFirstLoginForSocialLoginUser(FirstSocialRequestDto firstSocialRequestDto) throws IOException {

        User preUser = userRepository.findByUsername(firstSocialRequestDto.getUsername());
        preUser.changeNickname(firstSocialRequestDto.getNewNickname());
        userRepository.commit();

        User newUser = userRepository.findByUsername(firstSocialRequestDto.getUsername());

        return new JwtFirstSocialResponseDto(getJwtTokenFactoryInstance().getJwtToken(newUser),String.valueOf(newUser.getId()),newUser.getNickname(),newUser.getRole());




    }
}

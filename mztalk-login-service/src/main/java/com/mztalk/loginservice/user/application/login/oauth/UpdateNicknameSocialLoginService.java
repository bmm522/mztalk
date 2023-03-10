package com.mztalk.loginservice.user.application.login.oauth;

import com.mztalk.loginservice.user.api.dto.ClientFirstSocialRequestDto;
import com.mztalk.loginservice.user.application.login.dto.response.JwtFirstSocialResponseDto;
import com.mztalk.loginservice.user.repository.entity.User;
import com.mztalk.loginservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static com.mztalk.loginservice.user.factory.JwtTokenFactory.getJwtTokenFactoryInstance;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateNicknameSocialLoginService {

    private final UserRepository userRepository;
    public JwtFirstSocialResponseDto setFirstLoginForSocialLoginUser(ClientFirstSocialRequestDto firstSocialRequestDto) throws IOException {

        User preUser = userRepository.findByUsername(firstSocialRequestDto.getUsername());
        preUser.changeNickname(firstSocialRequestDto.getNewNickname());
        userRepository.commit();

        User newUser = userRepository.findByUsername(firstSocialRequestDto.getUsername());

        return new JwtFirstSocialResponseDto(getJwtTokenFactoryInstance().getJwtToken(newUser),String.valueOf(newUser.getId()),newUser.getNickname(),newUser.getRoleValue());




    }
}
